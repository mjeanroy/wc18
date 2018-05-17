package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.exceptions.MatchNotFoundException;
import com.github.mjeanroy.wc18.api.mappers.BetDtoMapper;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.services.BetService;
import com.github.mjeanroy.wc18.domain.services.MatchService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BetApiService {

	private final MatchService matchService;
	private final BetService betService;
	private final BetDtoMapper betDtoMapper;

	@Inject
	public BetApiService(MatchService matchService, BetService betService, BetDtoMapper betDtoMapper) {
		this.matchService = matchService;
		this.betService = betService;
		this.betDtoMapper = betDtoMapper;
	}

	/**
	 * Find all bets of given user.
	 *
	 * @param user The user.
	 * @return All bets, may be empty.
	 */
	public Iterable<BetDto> findAll(User user) {
		Iterable<Bet> bets = betService.findByUser(user);
		return betDtoMapper.from(bets);
	}

	/**
	 * Save new bet for given user.
	 *
	 * @param user The user.
	 * @param bet The bet.
	 * @return The result.
	 */
	public BetDto save(User user, BetDto bet) {
		String id = bet.getMatch().getId();
		Match match = matchService.findOne(id).orElseThrow(() ->
			new MatchNotFoundException(id)
		);

		int score1 = bet.getScore().getScore1();
		int score2 = bet.getScore().getScore2();
		return betDtoMapper.from(betService.save(user, match, score1, score2));
	}
}
