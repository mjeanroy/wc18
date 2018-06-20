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

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.exceptions.MatchLockedException;
import com.github.mjeanroy.wc18.domain.exceptions.MatchUnlockedException;
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
	 * Get all bets of given users for a given match.
	 *
	 * @param users The users.
	 * @param match The match.
	 * @return List of bets.
	 */
	@Transactional(readOnly = true)
	public Iterable<Bet> findByUsersAndMatch(Iterable<User> users, Match match) {
		if (!match.isLocked()) {
			throw new MatchUnlockedException(match.getId());
		}

		return betDao.findByUserInAndMatch(users, match);
	}

	/**
	 * Save bet for given user.
	 *
	 * @param user The user.
	 * @param match The match.
	 * @param score1 First score.
	 * @param score2 Second score.
	 * @param checkLocked Check if match is locked, and fail if it is.
	 * @return The bet.
	 */
	@Transactional
	public Bet save(User user, Match match, int score1, int score2, boolean checkLocked) {
		log.info("Saving bet for match #{} and user #{}", match, user);

		if (checkLocked && match.isLocked()) {
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
