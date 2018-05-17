/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.User;

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
	 * Build the final {@link User} instance.
	 *
	 * @return The instance.
	 */
	public User build() {
		User user = newInstance(User.class);
		writeField(user, "id", id);
		writeField(user, "login", login);
		writeField(user, "password", password);
		return user;
	}
}
