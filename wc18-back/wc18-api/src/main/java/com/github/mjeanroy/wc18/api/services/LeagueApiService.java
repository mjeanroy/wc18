/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.mappers.LeagueDtoMapper;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.services.LeagueService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class LeagueApiService {

	private final LeagueService leagueService;
	private final LeagueDtoMapper leagueDtoMapper;

	@Inject
	public LeagueApiService(LeagueService leagueService, LeagueDtoMapper leagueDtoMapper) {
		this.leagueService = leagueService;
		this.leagueDtoMapper = leagueDtoMapper;
	}

	public Iterable<LeagueDto> findAll() {
		Iterable<League> leagues = leagueService.findAll();
		return leagueDtoMapper.from(leagues);
	}

	public LeagueDto create(LeagueDto leagueDto) {
		League league = leagueService.create(leagueDto.getName());
		return leagueDtoMapper.from(league);
	}

	public void remove(String id) {
		leagueService.remove(id);
	}
}
