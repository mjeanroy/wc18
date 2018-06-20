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

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.dto.RankDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.mappers.BetDtoMapper;
import com.github.mjeanroy.wc18.api.mappers.LeagueDtoMapper;
import com.github.mjeanroy.wc18.api.mappers.RankDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.BetService;
import com.github.mjeanroy.wc18.domain.services.LeagueService;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import com.github.mjeanroy.wc18.domain.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class LeagueApiService {

	/**
	 * Class Logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(LeagueApiService.class);

	private final LeagueService leagueService;
	private final UserService userService;
	private final MatchService matchService;
	private final BetService betService;
	private final LeagueDtoMapper leagueDtoMapper;
	private final RankDtoMapper rankDtoMapper;
	private final BetDtoMapper betDtoMapper;

	@Inject
	public LeagueApiService(
		LeagueService leagueService,
		LeagueDtoMapper leagueDtoMapper,
		UserService userService,
		MatchService matchService,
		BetService betService,
		RankDtoMapper rankDtoMapper,
		BetDtoMapper betDtoMapper) {

		this.leagueService = leagueService;
		this.leagueDtoMapper = leagueDtoMapper;
		this.userService = userService;
		this.matchService = matchService;
		this.betService = betService;
		this.rankDtoMapper = rankDtoMapper;
		this.betDtoMapper = betDtoMapper;
	}

	/**
	 * Find all leagues.
	 *
	 * @return Leagues.
	 */
	@Transactional(readOnly = true)
	public Iterable<LeagueDto> findAll() {
		log.info("Find all leagues");
		Iterable<League> leagues = leagueService.findAll();
		return leagueDtoMapper.from(leagues);
	}

	/**
	 * Find league by its identifier.
	 *
	 * @param id League identifier.
	 * @return The league.
	 */
	@Transactional(readOnly = true)
	public LeagueDto findOne(String id) {
		log.info("Find league {}", id);
		League league = leagueService.findOneOrFail(id);
		return leagueDtoMapper.from(league);
	}

	/**
	 * Find all users in given league.
	 *
	 * @param id League identifier.
	 * @return All users.
	 */
	@Transactional(readOnly = true)
	public Iterable<UserDto> findUsers(String id) {
		log.info("Find user of league {}", id);
		return findOne(id).getUsers();
	}

	/**
	 * Find all users in given league.
	 *
	 * @param id League identifier.
	 * @return All users.
	 */
	@Transactional(readOnly = true)
	public Iterable<BetDto> findBets(String id, String matchId) {
		League league = leagueService.findOneOrFail(id);
		Match match = matchService.findOneOrFail(matchId);
		Iterable<Bet> bets = betService.findByUsersAndMatch(league.getUsers(), match);
		return betDtoMapper.from(bets);
	}

	/**
	 * Find all ranks in given league.
	 *
	 * @param id League identifier.
	 * @return All users.
	 */
	@Transactional(readOnly = true)
	public Iterable<RankDto> findRanks(String id) {
		League league = leagueService.findOneOrFail(id);
		Iterable<Rank> ranks = leagueService.findRanks(league);
		return rankDtoMapper.from(ranks);
	}

	/**
	 * Add user in given league.
	 *
	 * @param id League identifier.
	 * @param userId User identifier.
	 */
	@Transactional
	public void addUser(String id, String userId) {
		log.info("Add user #{} to league #{}", userId, id);
		League league = leagueService.findOneOrFail(id);
		User user = userService.findOneOrFail(userId);
		leagueService.addUser(league, user);
	}

	/**
	 * Create new leagues.
	 *
	 * @param leagueDto The league to create.
	 * @return The result.
	 */
	@Transactional
	public LeagueDto create(LeagueDto leagueDto) {
		log.info("Creating new league with name '{}'", leagueDto.getName());
		League league = leagueService.create(leagueDto.getName());
		return leagueDtoMapper.from(league);
	}

	/**
	 * Remove league.
	 *
	 * @param id League identifier.
	 */
	@Transactional
	public void remove(String id) {
		log.info("Remove league #{}", id);
		leagueService.remove(id);
	}
}
