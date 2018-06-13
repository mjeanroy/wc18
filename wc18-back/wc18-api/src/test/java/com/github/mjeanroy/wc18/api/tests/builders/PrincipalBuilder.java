/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.security.models.DefaultPrincipal;
import com.github.mjeanroy.wc18.security.models.Principal;

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
	 * The principal role.
	 * @see Principal#getRole()
	 */
	private String role;

	/**
	 * Set {@link #login}
	 *
	 * @param login New {@link #login}
	 * @return The builder.
	 */
	public PrincipalBuilder withLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Set {@link #role}
	 *
	 * @param role New {@link #role}
	 * @return The builder.
	 */
	public PrincipalBuilder withRole(String role) {
		this.role = role;
		return this;
	}

	/**
	 * Build final {@link Principal} instance.
	 *
	 * @return The final instance.
	 */
	public Principal build() {
		return new DefaultPrincipal(login, role);
	}
}
