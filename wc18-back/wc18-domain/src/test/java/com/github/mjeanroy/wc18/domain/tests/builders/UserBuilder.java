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

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.models.User.Role;

import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;

/**
 * Builder for {@link User} instances.
 */
public class UserBuilder {

	/**
	 * The user id.
	 * @see User#id
	 */
	private String id;

	/**
	 * The user login.
	 * @see User#login
	 */
	private String login;

	/**
	 * The user password.
	 * @see User#password
	 */
	private String password;

	/**
	 * The user role.
	 * @see User#role
	 */
	private Role role;

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 * @return The builder.
	 */
	public UserBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #id} with a random value.
	 *
	 * @return The current builder.
	 */
	public UserBuilder withRandomId() {
		this.id = UUID.randomUUID().toString();
		return this;
	}

	/**
	 * Set {@link #id}.
	 *
	 * @param login New {@link #login}
	 * @return The current builder.
	 */
	public UserBuilder withLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Set {@link #password}.
	 *
	 * @param password New {@link #password}
	 * @return The current builder.
	 */
	public UserBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * Set {@link #role}.
	 *
	 * @param role New {@link #role}
	 * @return The current builder.
	 */
	public UserBuilder withRole(Role role) {
		this.role = role;
		return this;
	}

	/**
	 * Build the final {@link User} instance.
	 *
	 * @return The instance.
	 */
	public User build() {
		User user = newInstance(User.class);
		writeField(user, "id", id);
		writeField(user, "login", login);
		writeField(user, "password", password);
		writeField(user, "role", role);
		return user;
	}
}
