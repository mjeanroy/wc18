/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.exceptions;

import com.github.mjeanroy.wc18.domain.exceptions.AbstractException;

public class LoginNotFoundException extends AbstractException {

	/**
	 * The unknown login.
	 */
	private final String login;

	/**
	 * Create the exception with given not found login.
	 *
	 * @param login The missing login.
	 */
	public LoginNotFoundException(String login) {
		super(String.format("Cannot find user for login '%s'", login));
		this.login = login;
	}

	/**
	 * Get {@link #login}
	 *
	 * @return {@link #login}
	 */
	public String getLogin() {
		return login;
	}
}
