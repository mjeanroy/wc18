/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.LoginDto;

/**
 * Builder for {@link LoginDto} instances.
 */
public class LoginDtoBuilder {

	/**
	 * The form login.
	 * @see LoginDto#login
	 */
	private String login;

	/**
	 * The form password.
	 * @see LoginDto#password
	 */
	private String password;

	/**
	 * Set {@link #login}
	 *
	 * @param login New {@link #login}
	 * @return The builder.
	 */
	public LoginDtoBuilder withLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Set {@link #password}
	 *
	 * @param password New {@link #password}
	 * @return The builder.
	 */
	public LoginDtoBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Build final {@link LoginDto} instance.
	 *
	 * @return The final instance.
	 */
	public LoginDto build() {
		LoginDto dto = new LoginDto();
		dto.setLogin(login);
		dto.setPassword(password);
		return dto;
	}
}
