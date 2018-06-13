/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.LoginDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.CredentialsNotFoundException;
import com.github.mjeanroy.wc18.api.tests.builders.HttpServletResponseBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.LoginDtoBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class LoginApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private SecurityService securityService;

	@Inject
	private LoginApiService loginApiService;

	@Test
	public void it_should_log_user() {
		String login = "mickael";
		String password = "azerty123";
		HttpServletResponse response = new HttpServletResponseBuilder().build();
		LoginDto dto = new LoginDtoBuilder().withLogin(login).withPassword(password).build();

		UserDto result = loginApiService.login(dto, response);

		assertThat(result).isNotNull();
		assertThat(result.getLogin()).isEqualTo(login);

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

		verifyZeroInteractions(securityService);
	}

	@Test
	public void it_should_logout() {
		HttpServletResponse response = new HttpServletResponseBuilder().build();
		loginApiService.logout(response);
		verify(securityService).logout(response);
	}
}
