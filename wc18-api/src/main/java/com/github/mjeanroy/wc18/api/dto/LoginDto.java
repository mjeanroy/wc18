/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

public class LoginDto {

	/**
	 * The login.
	 */
	private String login;

	/**
	 * The password.
	 */
	private String password;

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
	 *
	 * @param login New {@link #login}
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get {@link #password}
	 *
	 * @return {@link #password}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set {@link #password}
	 *
	 * @param password New {@link #password}
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
