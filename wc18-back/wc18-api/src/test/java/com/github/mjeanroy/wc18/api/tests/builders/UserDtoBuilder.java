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

import com.github.mjeanroy.wc18.api.dto.UserDto;

/**
 * Builder for {@link UserDto} instances.
 */
public class UserDtoBuilder {

	/**
	 * The user id.
	 * @see UserDto#id
	 */
	private String id;

	/**
	 * The user login.
	 * @see UserDto#login
	 */
	private String login;

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 * @return The builder.
	 */
	public UserDtoBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #login}
	 *
	 * @param login New {@link #login}
	 * @return The builder.
	 */
	public UserDtoBuilder withLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Build final {@link UserDto} instance.
	 *
	 * @return The final instance.
	 */
	public UserDto build() {
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setLogin(login);
		return dto;
	}
}
