package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.mappers.TeamDtoMapper;
import com.github.mjeanroy.wc18.domain.services.TeamService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TeamApiService {

	private final TeamService teamService;
	private final TeamDtoMapper teamDtoMapper;

	@Inject
	public TeamApiService(TeamService teamService, TeamDtoMapper teamDtoMapper) {
		this.teamService = teamService;
		this.teamDtoMapper = teamDtoMapper;
	}
}
