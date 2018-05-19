/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
