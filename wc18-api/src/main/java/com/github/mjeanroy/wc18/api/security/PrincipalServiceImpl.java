/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.security;

import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.models.Principal;
import com.github.mjeanroy.wc18.security.service.PrincipalService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	private final UserService userService;

	@Inject
	public PrincipalServiceImpl(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Get the principal by its login.
	 *
	 * @param login The login.
	 * @return The principal instance, if it exists.
	 */
	@Override
	public Optional<Principal> findByLogin(String login) {
		return userService.findByLogin(login).map(PrincipalImpl::new);
	}
}
