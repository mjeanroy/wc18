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

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.LoginDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.exceptions.CredentialsNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.UserDtoMapper;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.models.DefaultPrincipal;
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

		// Process authentication for the HTTP request.
		securityService.authenticate(response, new DefaultPrincipal(user.getLogin(), user.getRole().name()));

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
