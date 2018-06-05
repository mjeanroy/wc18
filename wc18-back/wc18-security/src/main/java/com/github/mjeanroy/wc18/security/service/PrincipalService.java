/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.security.service;

import com.github.mjeanroy.wc18.security.models.Principal;

import java.util.Optional;

/**
 * The service that can be used to find {@link Principal}
 * instances.
 */
public interface PrincipalService {

	/**
	 * Find principal user by its login.
	 *
	 * @param login The login.
	 * @return The principal, if it exists.
	 */
	Optional<Principal> findByLogin(String login);
}
