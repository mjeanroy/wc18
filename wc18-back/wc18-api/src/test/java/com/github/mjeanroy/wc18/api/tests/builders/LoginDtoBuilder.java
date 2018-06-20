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

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.LoginDto;

/**
 * Builder for {@link LoginDto} instances.
 */
public class LoginDtoBuilder {

	/**
	 * The form login.
	 *
	 * @see LoginDto#login
	 */
	private String login;

	/**
	 * The form password.
	 *
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
