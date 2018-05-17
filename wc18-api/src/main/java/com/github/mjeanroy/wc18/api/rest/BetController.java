/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.mappers.MatchDtoMapper;
import com.github.mjeanroy.wc18.api.services.BetApiService;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * The {@link MatchDto} Rest Controller.
 */
@RestController
@RequestMapping("/api/bets")
public class BetController {

	private final BetApiService betApiService;

	@Inject
	public BetController(BetApiService betApiService) {
		this.betApiService = betApiService;
	}

	@GetMapping
	public Iterable<BetDto> findAll(User user) {
		return betApiService.findAll(user);
	}

	@PostMapping
	public BetDto create(User user, @RequestBody BetDto bet) {
		return betApiService.save(user, bet);
	}

	@PutMapping("/{id}")
	public BetDto update(User user, @RequestBody BetDto bet) {
		return betApiService.save(user, bet);
	}
}
