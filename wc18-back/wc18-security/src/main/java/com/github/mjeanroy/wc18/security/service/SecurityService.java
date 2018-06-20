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

package com.github.mjeanroy.wc18.security.service;

import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.parsers.TokenParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * The service to use to proceed with security authentication.
 */
public class SecurityService {

	private final TokenParser tokenParser;
	private final PrincipalService principalService;

	public SecurityService(TokenParser tokenParser, PrincipalService principalService) {
		this.tokenParser = tokenParser;
		this.principalService = principalService;
	}

	/**
	 * Authenticate principal.
	 *
	 * @param response The HTTP Response.
	 * @param principal The authenticated user.
	 */
	public void authenticate(HttpServletResponse response, Principal principal) {
		tokenParser.put(response, principal.getLogin(), false);
	}

	/**
	 * Logout principal.
	 *
	 * @param response The HTTP Response.
	 */
	public void logout(HttpServletResponse response) {
		tokenParser.remove(response);
	}

	/**
	 * Log principal.
	 *
	 * @param request The HTTP request.
	 * @return The principal, if it exists.
	 */
	public Optional<Principal> getPrincipal(HttpServletRequest request) {
		String login = tokenParser.parse(request);
		if (login == null) {
			return Optional.empty();
		}

		return principalService.findByLogin(login);
	}
}
