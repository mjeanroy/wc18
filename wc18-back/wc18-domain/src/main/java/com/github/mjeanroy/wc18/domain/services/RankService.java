/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.dao.UserDao;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankService {

	private final UserDao userDao;
	private final BetDao betDao;

	@Inject
	public RankService(UserDao userDao, BetDao betDao) {
		this.userDao = userDao;
		this.betDao = betDao;
	}

	/**
	 * Get user ranks.
	 *
	 * @return Ranks.
	 */
	public Iterable<Rank> getRanks() {
		Map<User, Integer> scores = new HashMap<>();

		// Add everybody with a score of zero.
		Iterable<User> users = userDao.findAll();
		for (User user : users) {
			scores.put(user, 0);
		}

		// Now, get the result of each bet.
		// In case of performance problem, just store the score in database.
		Iterable<Bet> bets = betDao.findAll();
		for (Bet bet : bets) {
			User user = bet.getUser();
			int points = scores.get(user) + bet.getPoint();
			scores.put(user, points);
		}

		// Now, just return ranks.
		List<Rank> ranks = new ArrayList<>(scores.size());
		for (Map.Entry<User, Integer> entry : scores.entrySet()) {
			ranks.add(new Rank(entry.getKey(), entry.getValue()));
		}

		return ranks;
	}
}
