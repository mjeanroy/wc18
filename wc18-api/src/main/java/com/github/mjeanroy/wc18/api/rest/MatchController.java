/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.mappers.MatchDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class MatchController {

	private final MatchService matchService;
	private final MatchDtoMapper matchDtoMapper;

	@Inject
	public MatchController(MatchService matchService, MatchDtoMapper matchDtoMapper) {
		this.matchDtoMapper = matchDtoMapper;
		this.matchService = matchService;
	}

	@GetMapping
	public Iterable<MatchDto> findAll() {
		Iterable<Match> matches = matchService.findAll();
		return matchDtoMapper.map(matches);
	}
}
