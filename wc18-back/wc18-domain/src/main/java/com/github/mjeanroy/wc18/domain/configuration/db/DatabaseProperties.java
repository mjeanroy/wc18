/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
