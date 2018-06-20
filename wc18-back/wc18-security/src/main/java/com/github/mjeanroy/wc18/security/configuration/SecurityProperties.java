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
