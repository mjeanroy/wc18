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

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.mappers.MatchDtoMapper;
import com.github.mjeanroy.wc18.api.mappers.StageDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Stage;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import com.github.mjeanroy.wc18.domain.services.StageService;
import com.github.mjeanroy.wc18.domain.services.TeamService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service
public class MatchApiService {

	private final MatchService matchService;
	private final StageService stageService;
	private final MatchDtoMapper matchDtoMapper;
	private final StageDtoMapper stageDtoMapper;
	private final TeamService teamService;

	@Inject
	public MatchApiService(MatchService matchService, StageService stageService, MatchDtoMapper matchDtoMapper, StageDtoMapper stageDtoMapper, TeamService teamService) {
		this.matchService = matchService;
		this.stageService = stageService;
		this.matchDtoMapper = matchDtoMapper;
		this.stageDtoMapper = stageDtoMapper;
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
		Date date = matchDto.getDate();
		Stage stage = stageService.findOneOrFail(matchDto.getStage().getId());
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
		Date date = matchDto.getDate();
		Integer score1 = matchDto.getScore().getScore1();
		Integer score2 = matchDto.getScore().getScore2();
		Stage stage = stageService.findOneOrFail(matchDto.getStage().getId());
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
