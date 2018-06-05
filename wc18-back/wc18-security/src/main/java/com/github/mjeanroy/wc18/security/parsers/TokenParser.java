/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.parsers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenParser {

	/**
	 * Get authentication token from HTTP request.
	 *
	 * @param rq HTTP request.
	 * @return Token value, may be {@code null}.
	 */
	String parse(HttpServletRequest rq);

	/**
	 * Put authentication token into the HTTP response.
	 *
	 * @param rsp HTTP response.
	 * @param value Token value.
	 * @param rememberMe Used to enable persistent token between requests.
	 */
	void put(HttpServletResponse rsp, String value, boolean rememberMe);

	/**
	 * Remove authentication token.
	 *
	 * @param rsp HTTP response.
	 */
	void remove(HttpServletResponse rsp);
}
