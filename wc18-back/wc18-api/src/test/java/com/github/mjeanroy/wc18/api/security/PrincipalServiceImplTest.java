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

package com.github.mjeanroy.wc18.api.security;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.models.User.Role;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PrincipalServiceImplTest {

	private UserService userService;
	private PrincipalServiceImpl principalServiceImpl;

	@BeforeEach
	void setUp() {
		userService = mock(UserService.class);
		principalServiceImpl = new PrincipalServiceImpl(userService);
	}

	@Test
	void it_should_find_principal() {
		String login = "johndoe";
		Role role = Role.USER;
		createUser(login, role);

		Optional<Principal> principal = principalServiceImpl.findByLogin(login);

		assertThat(principal).isPresent();
		assertThat(principal.get().getLogin()).isEqualTo(login);
		verify(userService).findByLogin(login);
	}

	@Test
	void it_should_not_find_principal_if_user_does_not_exist() {
		String login = "johndoe";
		Optional<Principal> principal = principalServiceImpl.findByLogin(login);
		assertThat(principal).isNotPresent();
	}

	private void createUser(String login, Role role) {
		User user = new UserBuilder()
			.withRandomId()
			.withLogin(login)
			.withRole(role)
			.build();

		when(userService.findByLogin(login)).thenReturn(Optional.of(user));
	}
}
