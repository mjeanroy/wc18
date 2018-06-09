/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.configuration;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Security Properties.
 */
public final class SecurityProperties {

	/**
	 * The header name to use to read/write token value.
	 */
	private final String headerName;

	/**
	 * The internal secret key.
	 */
	private final String secret;

	public SecurityProperties(String headerName, String secret) {
		this.headerName = headerName;
		this.secret = secret;
	}

	/**
	 * Get {@link #headerName}
	 *
	 * @return {@link #headerName}
	 */
	public String getHeaderName() {
		return headerName;
	}

	/**
	 * Get {@link #secret}
	 *
	 * @return {@link #secret}
	 */
	public String getSecret() {
		return secret;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof SecurityProperties) {
			SecurityProperties p = (SecurityProperties) o;
			return Objects.equals(headerName, p.headerName)
					&& Objects.equals(secret, p.secret);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(headerName, secret);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("headerName", headerName)
				.add("secret", "*****")
				.toString();
	}
}
