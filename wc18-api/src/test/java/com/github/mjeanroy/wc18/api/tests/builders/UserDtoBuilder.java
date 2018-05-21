/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
