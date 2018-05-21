/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

public class UserDto extends AbstractDto {

	/**
	 * The user identifier.
	 */
	private String id;

	/**
	 * The user login.
	 */
	private String login;

	/**
	 * Get {@link #id}
	 *
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get {@link #login}
	 *
	 * @return {@link #login}
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set {@link #login}
	 * @param login New {@link #login}
	 */
	public void setLogin(String login) {
		this.login = login;
	}
}
