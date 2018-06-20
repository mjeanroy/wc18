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

package com.github.mjeanroy.wc18.domain.configuration.db;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Database/Datasource properties.
 */
public final class DatabaseProperties {

	/**
	 * Database connection URL.
	 */
	private final String url;

	/**
	 * Database connection user.
	 */
	private final String user;

	/**
	 * Database connection password.
	 */
	private final String password;

	/**
	 * Database JDBC driver.
	 */
	private final String driverClassName;

	/**
	 * Create property object.
	 *
	 * @param url Database connection URL.
	 * @param user Database connection user.
	 * @param password Database connection password.
	 * @param driverClassName Database JDBC driver.
	 */
	public DatabaseProperties(String url, String user, String password, String driverClassName) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driverClassName = driverClassName;
	}

	/**
	 * Get {@link #url}
	 *
	 * @return {@link #url}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Get {@link #user}
	 *
	 * @return {@link #user}
	 */
	public String getUser() {
		return user;
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
	 * Get {@link #driverClassName}
	 *
	 * @return {@link #driverClassName}
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof DatabaseProperties) {
			DatabaseProperties p = (DatabaseProperties) o;
			return Objects.equals(url, p.url)
					&& Objects.equals(user, p.user)
					&& Objects.equals(password, p.password)
					&& Objects.equals(driverClassName, p.driverClassName);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, user, password, driverClassName);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("url", url)
				.add("user", user)
				.add("password", "******")
				.add("driverClassName", driverClassName)
				.toString();
	}
}
