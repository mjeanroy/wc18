/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.exceptions.MatchLockedException;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

@Service
public class BetService {

	/**
	 * Class Logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(BetService.class);

	private final BetDao betDao;

	@Inject
	public BetService(BetDao betDao) {
		this.betDao = betDao;
	}

	/**
	 * Get all bets of given user.
	 *
	 * @param user The user.
	 * @return List of bets.
	 */
	@Transactional(readOnly = true)
	public Iterable<Bet> findByUser(User user, Boolean locked) {
		if (locked == null) {
			log.info("Find all bets for user #{}", user);
			return betDao.findByUser(user);
		} else if (locked) {
			log.info("Find bets (for locked matches) for user #{}", user);
			return betDao.findByUserAndMatchDateLessThan(user, new Date());
		} else {
			log.info("Find bets (for non locked matches) for user #{}", user);
			return betDao.findByUserAndMatchDateGreaterThanOrEqual(user, new Date());
		}
	}

	/**
	 * Save bet for given user.
	 *
	 * @param user The user.
	 * @param match The match.
	 * @param score1 First score.
	 * @param score2 Second score.
	 * @return The bet.
	 */
	@Transactional
	public Bet save(User user, Match match, int score1, int score2) {
		log.info("Saving bet for match #{} and user #{}", match, user);

		if (match.isLocked()) {
			log.error("Match #{} is locked, #{} cannot save bet", match, user);
			throw new MatchLockedException(match.getId());
		}

		Score score = new Score(score1, score2);
		Bet bet = betDao.findOne(user, match).orElseGet(() ->
			new Bet(user, match, score)
		);

		log.info("Updating score for bet #{}", bet);
		bet.updateScore(score);
		return betDao.save(bet);
	}
}
