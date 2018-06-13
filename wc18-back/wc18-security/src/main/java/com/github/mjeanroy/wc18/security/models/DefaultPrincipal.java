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
	 * The user role.
	 */
	private final String role;

	/**
	 * Create the principal instance from user login.
	 *
	 * @param login The login.
	 * @param role The role.
	 */
	public DefaultPrincipal(String login, String role) {
		this.login = login;
		this.role = role;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof DefaultPrincipal) {
			DefaultPrincipal p = (DefaultPrincipal) o;
			return Objects.equals(login, p.login) && Objects.equals(role, p.role);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, role);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("login", login)
				.add("role", role)
				.toString();
	}
}
