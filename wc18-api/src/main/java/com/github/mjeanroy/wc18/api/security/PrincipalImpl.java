package com.github.mjeanroy.wc18.api.security;

import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.security.models.Principal;

/**
 * Simple implementation of {@link Principal} using {@link User}
 * under the hood.
 */
class PrincipalImpl implements Principal {

	/**
	 * The user.
	 */
	private final User user;

	/**
	 * Create the principal instance from user.
	 *
	 * @param user The user.
	 */
	PrincipalImpl(User user) {
		this.user = user;
	}

	@Override
	public String getLogin() {
		return user.getLogin();
	}
}
