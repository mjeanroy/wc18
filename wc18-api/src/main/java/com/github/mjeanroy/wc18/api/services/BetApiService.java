/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.exceptions.LoginNotFoundException;
import com.github.mjeanroy.wc18.api.exceptions.MatchNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.BetDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.BetService;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import com.github.mjeanroy.wc18.domain.services.UserService;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BetApiService {

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
	public Iterable<BetDto> findAll(Principal principal) {
		User user = getUserOrFail(principal);
		Iterable<Bet> bets = betService.findByUser(user);
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
		User user = getUserOrFail(principal);

		String id = bet.getMatch().getId();
		Match match = matchService.findOne(id).orElseThrow(() ->
			new MatchNotFoundException(id)
		);

		int score1 = bet.getScore().getScore1();
		int score2 = bet.getScore().getScore2();
		Bet savedBet = betService.save(user, match, score1, score2);
		return betDtoMapper.from(savedBet);
	}

	private User getUserOrFail(Principal principal) {
		String login = principal.getLogin();
		return userService.findByLogin(login).orElseThrow(() ->
			new LoginNotFoundException(login)
		);
	}
}
