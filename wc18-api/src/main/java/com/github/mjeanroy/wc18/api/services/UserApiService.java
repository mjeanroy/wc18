/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.PasswordDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.PrincipalNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.UserDtoMapper;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class UserApiService {

	private final UserService userService;
	private final UserDtoMapper userDtoMapper;

	@Inject
	public UserApiService(UserService userService, UserDtoMapper userDtoMapper) {
		this.userService = userService;
		this.userDtoMapper = userDtoMapper;
	}

	/**
	 * Find the principal user.
	 *
	 * @param principal The principal.
	 * @return The authenticated user.
	 */
	public UserDto findOne(Principal principal) {
		return findOne(principal.getLogin()).orElseThrow(() ->
			new PrincipalNotFoundException(principal)
		);
	}

	/**
	 * Find user by its login.
	 *
	 * @param login The user login.
	 * @return The user.
	 */
	public Optional<UserDto> findOne(String login) {
		return userService.findByLogin(login).map(userDtoMapper::from);
	}

	/**
	 * Update user password.
	 *
	 * @param principal The principal user, i.e the authenticated user.
	 * @param passwords The old and new password.
	 */
	public void updatePassword(Principal principal, PasswordDto passwords) {
		User user = userService.findByLoginAndPassword(principal.getLogin(), passwords.getOldPassword()).orElseThrow(() ->
			new PrincipalNotFoundException(principal)
		);

		userService.updatePassword(user, passwords.getNewPassword());
	}
}
