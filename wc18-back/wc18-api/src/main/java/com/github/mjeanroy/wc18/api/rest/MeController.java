/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.dto.PasswordDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.services.UserApiService;
import com.github.mjeanroy.wc18.security.Security;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/me")
public class MeController {

	private final UserApiService userApiService;

	@Inject
	public MeController(UserApiService userApiService) {
		this.userApiService = userApiService;
	}

	@GetMapping
	@Security
	public UserDto me(Principal principal) {
		return userApiService.findOne(principal);
	}

	@GetMapping("/leagues")
	@Security
	public Iterable<LeagueDto> findLeagues(Principal principal) {
		return userApiService.findLeagues(principal);
	}

	@PostMapping("/password")
	@Security
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePassword(Principal principal, @RequestBody @Valid PasswordDto passwords) {
		userApiService.updatePassword(principal, passwords);
	}
}
