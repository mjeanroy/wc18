/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.impl.spring.SpringMapper;
import com.github.mjeanroy.wc18.api.dto.PasswordDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.PrincipalNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.UserDtoMapper;
import com.github.mjeanroy.wc18.api.tests.builders.PasswordDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.PrincipalBuilder;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserApiServiceTest {

	private UserService userService;
	private UserApiService userApiService;

	@Before
	public void setUp() {
		userService = mock(UserService.class);

		Mapper mapper = new SpringMapper();
		UserDtoMapper userDtoMapper = new UserDtoMapper(mapper);
		userApiService = new UserApiService(userService, userDtoMapper);
	}

	@Test
	public void it_should_find_user_by_its_login() {
		String login = "johndoe";
		createUser(login);

		Optional<UserDto> optResult = userApiService.findOne(login);

		assertThat(optResult).isPresent();
		assertThat(optResult.get().getLogin()).isEqualTo(login);
		verify(userService).findByLogin(login);
	}

	@Test
	public void it_should_not_find_unexisting_login() {
		String login = "johndoe";
		Optional<UserDto> optResult = userApiService.findOne(login);
		assertThat(optResult).isNotPresent();
		verify(userService).findByLogin(login);
	}

	@Test
	public void it_should_principal() {
		String login = "johndoe";
		Principal principal = new PrincipalBuilder().withLogin(login).build();
		createPrincipal(principal);

		UserDto user = userApiService.findOne(principal);

		assertThat(user).isNotNull();
		assertThat(user.getLogin()).isEqualTo(login);
		verify(userService).findByLogin(login);
	}

	@Test
	public void it_should_fail_of_principal_does_not_exist() {
		String login = "johndoe";
		Principal principal = new PrincipalBuilder().withLogin(login).build();
		assertThatThrownBy(() -> userApiService.findOne(principal))
			.isExactlyInstanceOf(PrincipalNotFoundException.class)
			.hasMessage("Cannot find principal user for login 'johndoe'");
	}

	@Test
	public void it_should_update_user_password() {
		String login = "johndoe";
		String oldPassword = "azerty123";
		String newPassword = "qwerty123";

		Principal principal = new PrincipalBuilder().withLogin(login).build();
		createPrincipal(principal);

		PasswordDto passwords = new PasswordDtoBuilder()
			.withOldPassword(oldPassword)
			.withNewPassword(newPassword)
			.build();

		User user = createUser(login, oldPassword);

		userApiService.updatePassword(principal, passwords);

		verify(userService).findByLoginAndPassword(login, oldPassword);
		verify(userService).updatePassword(user, newPassword);
	}

	@Test
	public void it_should_not_update_user_password_if_login_password_tuple_does_not_exist() {
		String login = "johndoe";
		String oldPassword = "azerty123";
		String newPassword = "qwerty123";
		Principal principal = new PrincipalBuilder().withLogin(login).build();

		PasswordDto passwords = new PasswordDtoBuilder()
			.withOldPassword(oldPassword)
			.withNewPassword(newPassword)
			.build();

		assertThatThrownBy(() -> userApiService.updatePassword(principal, passwords))
			.isExactlyInstanceOf(PrincipalNotFoundException.class)
			.hasMessage("Cannot find principal user for login 'johndoe'");

		verify(userService).findByLoginAndPassword(login, oldPassword);
		verify(userService, never()).updatePassword(any(User.class), anyString());
	}

	private User createUser(String login, String password) {
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.withPassword(password)
			.build();

		when(userService.findByLogin(login)).thenReturn(Optional.of(user));
		when(userService.findByLoginAndPassword(login, password)).thenReturn(Optional.of(user));

		return user;
	}

	private void createUser(String login) {
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.build();

		when(userService.findByLogin(login)).thenReturn(Optional.of(user));
	}

	private void createPrincipal(Principal principal) {
		String login = principal.getLogin();
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.build();

		when(userService.findByLogin(login)).thenReturn(Optional.of(user));
	}
}
