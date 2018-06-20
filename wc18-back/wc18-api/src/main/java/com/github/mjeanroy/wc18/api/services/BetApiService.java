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
import com.github.mjeanroy.wc18.api.exceptions.LoginNotFoundException;
import com.github.mjeanroy.wc18.domain.exceptions.MatchNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.BetDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.BetService;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BetApiService {

	/**
	 * Class Logger.
	 */
	private static final Logger log = LoggerFactory.getLogger(BetApiService.class);

	private final UserService userService;
	private final MatchService matchService;
	private final BetService betService;
	private final BetDtoMapper betDtoMapper;

	@Inject
	public BetApiService(UserService userService, MatchService matchService, BetService betService, BetDtoMapper betDtoMapper) {
		this.userService = userService;
		this.matchService = matchService;
		this.betService = betService;
		this.betDtoMapper = betDtoMapper;
	}

	/**
	 * Find all bets of given user.
	 *
	 * @param principal The principal.
	 * @return All bets, may be empty.
	 */
	public Iterable<BetDto> findAll(Principal principal, Boolean locked) {
		User user = getUserOrFail(principal);
		Iterable<Bet> bets = betService.findByUser(user, locked);
		return betDtoMapper.from(bets);
	}

	/**
	 * Save new bet for given user.
	 *
	 * @param principal The principal.
	 * @param bet The bet.
	 * @return The result.
	 */
	public BetDto save(Principal principal, BetDto bet) {
		log.info("Saving bet for principal: {}", principal);
		User user = getUserOrFail(principal);
		return save(user, bet, true);
	}

	/**
	 * Save new bet for given user.
	 *
	 * @param userId The user identifier.
	 * @param bet The bet.
	 * @return The result.
	 */
	public BetDto save(String userId, BetDto bet) {
		log.info("Saving bet for user: {}", userId);
		User user = userService.findOneOrFail(userId);
		return save(user, bet, false);
	}

	private BetDto save(User user, BetDto bet, boolean checkLocked) {
		String matchId = bet.getMatch().getId();
		Match match = matchService.findOne(matchId).orElseThrow(() ->
				new MatchNotFoundException(matchId)
		);

		int score1 = bet.getScore().getScore1();
		int score2 = bet.getScore().getScore2();

		log.info("Saving bet for match #{}: {} - {}", match, score1, score2);
		Bet savedBet = betService.save(user, match, score1, score2, false);

		log.info("Bet saved, return DTO");
		return betDtoMapper.from(savedBet);
	}

	private User getUserOrFail(Principal principal) {
		String login = principal.getLogin();
		return userService.findByLogin(login).orElseThrow(() ->
			new LoginNotFoundException(login)
		);
	}
}
