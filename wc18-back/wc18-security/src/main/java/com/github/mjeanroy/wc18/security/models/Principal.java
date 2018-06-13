/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.models;

public interface Principal {

	/**
	 * The principal login.
	 *
	 * @return The login.
	 */
	String getLogin();

	/**
	 * The principal role.
	 *
	 * @return The role.
	 */
	String getRole();
}
