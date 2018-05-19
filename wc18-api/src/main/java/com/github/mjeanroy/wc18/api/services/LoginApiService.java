/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.CredentialsNotFoundException;
import com.github.mjeanroy.wc18.api.dto.LoginDto;
import com.github.mjeanroy.wc18.api.mappers.UserDtoMapper;
import com.github.mjeanroy.wc18.api.security.PrincipalImpl;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.service.SecurityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginApiService {

	private final SecurityService securityService;
	private final UserService userService;
	private final UserDtoMapper userDtoMapper;

	@Inject
	public LoginApiService(SecurityService securityService, UserService userService, UserDtoMapper userDtoMapper) {
		this.securityService = securityService;
		this.userService = userService;
		this.userDtoMapper = userDtoMapper;
	}

	/**
	 * Authenticate user.
	 *
	 * @param login Login.
	 * @param response The HTTP response.
	 * @return The authenticated user.
	 */
	public UserDto login(LoginDto login, HttpServletResponse response) {
		User user = userService.findByLoginAndPassword(login.getLogin(), login.getPassword()).orElseThrow(
			CredentialsNotFoundException::new
		);

		// Process authentication.
		securityService.authenticate(response, new PrincipalImpl(user));

		return userDtoMapper.from(user);
	}

	/**
	 * Logout current user.
	 *
	 * @param response The HTTP response.
	 */
	public void logout(HttpServletResponse response) {
		securityService.logout(response);
	}
}
