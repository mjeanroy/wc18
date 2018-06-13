/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.spring.mappers.impl.spring.SpringMapper;
import com.github.mjeanroy.wc18.api.dto.LoginDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.CredentialsNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.UserDtoMapper;
import com.github.mjeanroy.wc18.api.tests.builders.HttpServletResponseBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.LoginDtoBuilder;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.models.User.Role;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class LoginApiServiceTest {

	private UserService userService;
	private SecurityService securityService;
	private LoginApiService loginApiService;

	@Before
	public void setUp() {
		userService = mock(UserService.class);
		securityService = mock(SecurityService.class);

		UserDtoMapper userDtoMapper = new UserDtoMapper(new SpringMapper());
		loginApiService = new LoginApiService(securityService, userService, userDtoMapper);
	}

	@Test
	public void it_should_log_user() {
		String login = "mickael";
		String password = "azerty123";
		Role role = Role.USER;
		HttpServletResponse response = new HttpServletResponseBuilder().build();
		LoginDto dto = new LoginDtoBuilder().withLogin(login).withPassword(password).build();

		createUser(login, password, role);

		UserDto result = loginApiService.login(dto, response);

		assertThat(result).isNotNull();
		assertThat(result.getLogin()).isEqualTo(login);
		verify(userService).findByLoginAndPassword(login, password);

		ArgumentCaptor<Principal> argCaptor = ArgumentCaptor.forClass(Principal.class);
		verify(securityService).authenticate(same(response), argCaptor.capture());

		Principal principal = argCaptor.getValue();
		assertThat(principal).isNotNull();
		assertThat(principal.getLogin()).isEqualTo(login);
	}

	@Test
	public void it_should_fail_with_bad_credentials() {
		String login = "mickael";
		String password = "azerty123";
		HttpServletResponse response = new HttpServletResponseBuilder().build();
		LoginDto dto = new LoginDtoBuilder().withLogin(login).withPassword(password).build();

		assertThatThrownBy(() -> loginApiService.login(dto, response))
			.isExactlyInstanceOf(CredentialsNotFoundException.class)
			.hasMessage("Bad credentials");

		verify(userService).findByLoginAndPassword(login, password);
		verifyZeroInteractions(securityService);
	}

	@Test
	public void it_should_logout() {
		HttpServletResponse response = new HttpServletResponseBuilder().build();
		loginApiService.logout(response);
		verify(securityService).logout(response);
	}

	private void createUser(String login, String password, Role role) {
		User user = new UserBuilder()
				.withRandomId()
				.withLogin(login)
				.withPassword(password)
				.withRole(role)
				.build();

		when(userService.findByLoginAndPassword(login, password)).thenReturn(Optional.of(user));
	}
}
