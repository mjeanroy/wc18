/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.UserDao;
import com.github.mjeanroy.wc18.domain.exceptions.UserNotFoundException;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.mjeanroy.wc18.domain.tests.commons.ReflectionTestUtils.writeField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class UserServiceTest extends AbstractServiceTest {

	private static final String PWD_PREFIX = "encoded:";

	@Mock
	private PasswordService passwordService;

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserService userService;

	@Before
	public void initPasswordService() {
		when(passwordService.encode(anyString())).thenAnswer(invocationOnMock ->
			PWD_PREFIX + invocationOnMock.getArgument(0)
		);

		when(passwordService.match(anyString(), anyString())).thenAnswer(invocationOnMock -> {
			String plainText = invocationOnMock.getArgument(0);
			String hash = invocationOnMock.getArgument(1);
			return hash.equals(PWD_PREFIX + plainText);
		});
	}

	@Before
	public void initUserDao() {
		when(userDao.save(any(User.class))).thenAnswer(invocation -> {
			User user = invocation.getArgument(0);
			if (user.getId() == null) {
				writeField(user, "id", UUID.randomUUID().toString());
			}

			return user;
		});
	}

	@Test
	public void it_should_find_user_by_its_login_and_password() {
		String login = "mickael";
		String plainText = "azerty123";
		String hashPassword = PWD_PREFIX + plainText;
		User user = createUser(login, hashPassword);

		Optional<User> optResult = userService.findByLoginAndPassword(login, plainText);

		assertThat(optResult).isPresent().hasValue(user);
		verify(userDao).findByLogin(login);
		verify(passwordService).match(plainText, hashPassword);
	}

	@Test
	public void it_should_not_find_user_by_its_login_and_wrong_password() {
		String login = "mickael";
		String plainText = "azerty123";
		String password = "qwerty123";
		String hashPassword = PWD_PREFIX + password;
		createUser(login, hashPassword);

		Optional<User> optResult = userService.findByLoginAndPassword(login, plainText);

		assertThat(optResult).isNotPresent();
		verify(userDao).findByLogin(login);
		verify(passwordService).match(plainText, hashPassword);
	}

	@Test
	public void it_should_not_find_unknown_login() {
		String login = "mickael";
		String plainText = "azerty123";

		Optional<User> optResult = userService.findByLoginAndPassword(login, plainText);

		assertThat(optResult).isNotPresent();
		verify(userDao).findByLogin(login);
		verifyZeroInteractions(passwordService);
	}

	@Test
	public void it_should_update_user_password() {
		String newPassword = "qwerty123";
		User user = createUser("johndoe", "azerty123");

		userService.updatePassword(user, newPassword);

		ArgumentCaptor<User> argCaptor = ArgumentCaptor.forClass(User.class);
		verify(userDao).save(argCaptor.capture());

		User savedUser = argCaptor.getValue();
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getPassword()).isEqualTo(PWD_PREFIX + newPassword);

		InOrder inOrder = inOrder(passwordService, userDao);
		inOrder.verify(passwordService).encode(newPassword);
		inOrder.verify(userDao).save(user);
	}

	@Test
	public void it_should_find_all_users() {
		List<User> users = createRandomUsers();
		Iterable<User> results = userService.findAll();
		assertThat(results).isSameAs(users);
		verify(userDao).findAll();
	}

	@Test
	public void it_should_create_user() {
		String login = "johndoe";
		String password = "azerty123";
		User user = userService.create(login, password);

		assertThat(user).isNotNull();
		assertThat(user.getId()).isNotNull();
		assertThat(user.getLogin()).isEqualTo(login);
		assertThat(user.getPassword()).isEqualTo(PWD_PREFIX + "azerty123");

		verify(userDao).save(user);
		verify(passwordService).encode(password);
	}

	@Test
	public void it_should_remove_user() {
		User user = createUser("mickael", "azerty123");
		userService.remove(user.getId());
		verify(userDao).delete(user);
	}

	@Test
	public void it_should_fail_to_remove_user_if_user_does_not_exist() {
		String id = "16e4f8e0-308e-4ebc-a85b-df223733e2f3";
		User user = new UserBuilder().withId(id).build();
		assertThatThrownBy(() -> userService.remove(user.getId()))
				.isExactlyInstanceOf(UserNotFoundException.class)
				.hasMessage("Cannot find user '" + id + "'");
	}

	private List<User> createRandomUsers() {
		List<User> users = IntStream.iterate(0, i -> i++)
				.mapToObj(i -> createUser("login_" + i, "azerty123"))
				.limit(10)
				.collect(Collectors.toList());

		when(userDao.findAll()).thenReturn(users);

		return users;
	}

	private User createUser(String login, String password) {
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.withPassword(password)
			.build();

		when(userDao.findOne(user.getId())).thenReturn(Optional.of(user));
		when(userDao.findByLogin(login)).thenReturn(Optional.of(user));

		return user;
	}
}
