/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.mappers.MatchDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Match.Stage;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import com.github.mjeanroy.wc18.domain.services.TeamService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service
public class MatchApiService {

	private final MatchService matchService;
	private final MatchDtoMapper matchDtoMapper;
	private final TeamService teamService;

	@Inject
	public MatchApiService(MatchService matchService, MatchDtoMapper matchDtoMapper, TeamService teamService) {
		this.matchService = matchService;
		this.matchDtoMapper = matchDtoMapper;
		this.teamService = teamService;
	}

	/**
	 * Find all matches.
	 *
	 * @param locked A flag to filter by locked/non-locked matches, if not {@code null}.
	 * @return The list of matches.
	 */
	public Iterable<MatchDto> findAll(Boolean locked) {
		Iterable<Match> matches = matchService.findAll(locked);
		return matchDtoMapper.from(matches);
	}

	/**
	 * Update match.
	 *
	 * @param matchDto New match.
	 * @return The result.
	 */
	public MatchDto create(MatchDto matchDto) {
		Stage stage = matchDto.getStage();
		Date date = matchDto.getDate();
		Team team1 = teamService.findOneOrFail(matchDto.getTeam1().getId());
		Team team2 = teamService.findOneOrFail(matchDto.getTeam2().getId());
		Match result = matchService.create(date, stage, team1, team2);
		return matchDtoMapper.from(result);
	}

	/**
	 * Update match.
	 *
	 * @param matchDto New match.
	 * @return The result.
	 */
	public MatchDto update(String id, MatchDto matchDto) {
		Stage stage = matchDto.getStage();
		Date date = matchDto.getDate();
		Integer score1 = matchDto.getScore().getScore1();
		Integer score2 = matchDto.getScore().getScore2();
		Match result = matchService.update(id, date, stage, score1, score2);
		return matchDtoMapper.from(result);
	}

	/**
	 * Remove match.
	 *
	 * @param id Match identifier.
	 */
	public void remove(String id) {
		matchService.remove(id);
	}
}
