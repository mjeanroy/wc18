/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.models;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Simple implementation of {@link Principal}.
 */
public final class DefaultPrincipal implements Principal {

	/**
	 * The user login.
	 */
	private final String login;

	/**
	 * Create the principal instance from user login.
	 *
	 * @param login The login.
	 */
	public DefaultPrincipal(String login) {
		this.login = login;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof DefaultPrincipal) {
			DefaultPrincipal p = (DefaultPrincipal) o;
			return Objects.equals(login, p.login);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
			.add("login", login)
			.toString();
	}
}
