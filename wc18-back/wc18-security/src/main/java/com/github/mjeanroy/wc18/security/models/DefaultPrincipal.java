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
