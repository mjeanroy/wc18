/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.services.BetApiService;
import com.github.mjeanroy.wc18.security.Security;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * The {@link MatchDto} Rest Controller.
 */
@RestController
public class BetController {

	private final BetApiService betApiService;

	@Inject
	public BetController(BetApiService betApiService) {
		this.betApiService = betApiService;
	}

	@GetMapping("/api/me/bets")
	@Security
	public Iterable<BetDto> findAll(
			@RequestParam(name = "locked", required = false) Boolean locked,
			Principal principal) {

		return betApiService.findAll(principal, locked);
	}

	@PostMapping("/api/me/bets")
	@Security
	public BetDto create(Principal principal, @RequestBody @Valid BetDto bet) {
		return betApiService.save(principal, bet);
	}

	@PutMapping("/api/me/bets/{id}")
	@Security
	public BetDto update(Principal principal, @RequestBody @Valid BetDto bet) {
		return betApiService.save(principal, bet);
	}

	@PostMapping("/api/users/{userId}/bets")
	@Security(role = "ADMIN")
	public BetDto update(@PathVariable("userId") String userId, @RequestBody @Valid BetDto bet) {
		return betApiService.save(userId, bet);
	}
}
