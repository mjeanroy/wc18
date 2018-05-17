/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class BetService {

	private final BetDao betDao;

	@Inject
	public BetService(BetDao betDao) {
		this.betDao = betDao;
	}

	@Transactional(readOnly = true)
	public Iterable<Bet> findByUser(User user) {
		return betDao.findByUser(user);
	}

	@Transactional
	public Bet save(User user, Match match, int score1, int score2) {
		Score score = new Score(score1, score2);
		Bet bet = betDao.findOne(user, match).orElseGet(() ->
			new Bet(user, match, score)
		);

		bet.updateScore(score);
		return betDao.save(bet);
	}
}
