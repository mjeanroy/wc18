/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.rest;

import com.github.mjeanroy.wc18.api.dto.TeamDto;
import com.github.mjeanroy.wc18.api.mappers.TeamDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.services.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * The {@link TeamDto} Rest Controller.
 */
@RestController
@RequestMapping("/api/teams")
public class TeamController {

	private final TeamService teamService;
	private final TeamDtoMapper teamDtoMapper;

	@Inject
	public TeamController(TeamService teamService, TeamDtoMapper teamDtoMapper) {
		this.teamService = teamService;
		this.teamDtoMapper = teamDtoMapper;
	}

	@GetMapping
	public Iterable<TeamDto> findAll() {
		Iterable<Team> teams = teamService.findAll();
		return teamDtoMapper.from(teams);
	}
}
