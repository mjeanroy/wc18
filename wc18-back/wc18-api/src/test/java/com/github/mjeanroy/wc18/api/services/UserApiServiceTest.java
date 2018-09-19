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
import com.github.mjeanroy.wc18.api.dto.PasswordDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.PrincipalNotFoundException;
import com.github.mjeanroy.wc18.api.tests.builders.LoginDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.PasswordDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.PrincipalBuilder;
import com.github.mjeanroy.wc18.api.tests.junit.AbstractApiServiceTest;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private UserApiService userApiService;

	@Test
	void it_should_find_principal() {
		String login = "mickael";
		Principal principal = new PrincipalBuilder()
			.withLogin(login)
			.build();

		UserDto user = userApiService.findOne(principal);

		assertThat(user).isNotNull();
		assertThat(user.getLogin()).isEqualTo(login);
	}

	@Test
	void it_should_find_all_users() {
		Iterable<UserDto> users = userApiService.findAll();
		assertThat(users).isNotEmpty();
	}

	@Test
	void it_should_not_find_non_existing_login() {
		String login = "fake_account";
		Principal principal = new PrincipalBuilder()
			.withLogin(login)
			.build();

		assertThatThrownBy(() -> userApiService.findOne(principal))
			.isExactlyInstanceOf(PrincipalNotFoundException.class)
			.hasMessage("Cannot find principal user for login '" + login + "'");
	}

	@Test
	void it_should_update_user_password() {
		String login = "mickael";
		String oldPassword = "azerty123";
		String newPassword = "qwerty123";
		Principal principal = new PrincipalBuilder().withLogin(login).build();

		PasswordDto passwords = new PasswordDtoBuilder()
			.withOldPassword(oldPassword)
			.withNewPassword(newPassword)
			.build();

		String id = "e31195bd-1d4e-4915-a3dc-ce901f57903f";
		String previousPassword = findOne(User.class, id).getPassword();

		userApiService.updatePassword(principal, passwords);

		// Ensure password have been updated
		String updatedPassword = findOne(User.class, id).getPassword();
		assertThat(updatedPassword).isNotEqualTo(previousPassword);
	}

	@Test
	void it_should_not_update_user_password_if_login_password_tuple_does_not_exist() {
		String login = "mickael";
		String oldPassword = "fake_account";
		String newPassword = "qwerty123";
		Principal principal = new PrincipalBuilder().withLogin(login).build();

		PasswordDto passwords = new PasswordDtoBuilder()
			.withOldPassword(oldPassword)
			.withNewPassword(newPassword)
			.build();

		assertThatThrownBy(() -> userApiService.updatePassword(principal, passwords))
			.isExactlyInstanceOf(PrincipalNotFoundException.class)
			.hasMessage("Cannot find principal user for login '" + login + "'");
	}

	@Test
	void it_should_create_user() {
		LoginDto loginDto = new LoginDtoBuilder()
			.withLogin("awesome_user")
			.withPassword("qwerty123")
			.build();

		UserDto user = userApiService.create(loginDto);

		assertThat(user).isNotNull();
		assertThat(user.getId()).isNotNull();
		assertThat(user.getLogin()).isNotNull();
	}

	@Test
	void it_should_remove_user() {
		String id = "e31195bd-1d4e-4915-a3dc-ce901f57903f";
		userApiService.remove(id);
		assertThat(findOne(User.class, id)).isNull();
	}
}
