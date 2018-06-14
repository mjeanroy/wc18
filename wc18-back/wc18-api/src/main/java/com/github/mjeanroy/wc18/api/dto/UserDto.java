/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

import com.github.mjeanroy.wc18.domain.models.User.Role;

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
	 * The user role.
	 */
	private Role role;

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

	/**
	 * Get {@link #role}
	 *
	 * @return {@link #role}
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set {@link #role}
	 *
	 * @param role New {@link #role}
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
