/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.exceptions;

import com.github.mjeanroy.wc18.domain.exceptions.AbstractException;
import com.github.mjeanroy.wc18.security.models.Principal;

public class PrincipalNotFoundException extends AbstractException {

	/**
	 * The unknown login.
	 */
	private final Principal principal;

	/**
	 * Create the exception with given not found principal user.
	 *
	 * @param principal The missing principal.
	 */
	public PrincipalNotFoundException(Principal principal) {
		super(String.format("Cannot find principal user for login '%s'", principal.getLogin()));
		this.principal = principal;
	}

	/**
	 * Get {@link #principal}
	 *
	 * @return {@link #principal}
	 */
	public Principal getPrincipal() {
		return principal;
	}
}
