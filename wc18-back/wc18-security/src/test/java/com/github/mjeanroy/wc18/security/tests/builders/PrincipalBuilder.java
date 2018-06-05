/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.tests.builders;

import com.github.mjeanroy.wc18.security.models.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Builder for {@link Principal} instances.
 */
public class PrincipalBuilder {

	/**
	 * The principal login.
	 * @see Principal#getLogin()
	 */
	private String login;

	/**
	 * Set {@link #login}
	 *
	 * @param login New {@link #login}
	 * @return The current builder.
	 */
	public PrincipalBuilder withLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Build final {@link Principal} instance.
	 *
	 * @return The final instance.
	 */
	public Principal build() {
		Principal principal = mock(Principal.class);
		when(principal.getLogin()).thenReturn(login);
		return principal;
	}
}