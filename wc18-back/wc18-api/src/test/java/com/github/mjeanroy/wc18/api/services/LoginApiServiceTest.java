/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.LoginDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.CredentialsNotFoundException;
import com.github.mjeanroy.wc18.api.tests.builders.HttpServletResponseBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.LoginDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.junit.AbstractApiServiceTest;
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
		String password = "qwerty123";
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
