/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankService {

	private final MatchDao matchDao;
	private final BetDao betDao;

	@Inject
	public RankService(MatchDao matchDao, BetDao betDao) {
		this.matchDao = matchDao;
		this.betDao = betDao;
	}

	/**
	 * Get user ranks.
	 *
	 * @return Ranks.
	 */
	public Iterable<Rank> getRanks(Iterable<User> users) {
		Map<User, RankStatistic> scores = new HashMap<>();

		// Add everybody with a score of zero.
		for (User user : users) {
			scores.put(user, new RankStatistic());
		}

		Date now = new Date();

		// Now, get the result of each bet.
		// In case of performance problem, just store the score in database.
		long nbMatches = matchDao.countByDateLessThanOrderByDate(now);
		Iterable<Bet> bets = betDao.findByUserInAndMatchDateLessThan(users, now);

		for (Bet bet : bets) {
			User user = bet.getUser();
			RankStatistic statistic = scores.get(user);

			Bet.Result result = bet.getResult();
			statistic.score += result.getPoint();

			if (result == Bet.Result.PERFECT) {
				statistic.nbGood++;
				statistic.nbPerfect++;
			} else if (result == Bet.Result.WIN) {
				statistic.nbGood++;
			}
		}

		// Now, just return ranks.
		List<Rank> ranks = new ArrayList<>(scores.size());
		for (Map.Entry<User, RankStatistic> entry : scores.entrySet()) {
			User user = entry.getKey();
			RankStatistic statistic = entry.getValue();
			int score = statistic.score;
			int percentGood = Math.toIntExact(statistic.nbGood * 100 / nbMatches);
			int percentPerfect = Math.toIntExact(statistic.nbPerfect * 100 / nbMatches);
			ranks.add(new Rank(user, score, percentGood, percentPerfect));
		}

		ranks.sort(Comparator.comparing(Rank::getScore));

		return ranks;
	}

	private static class RankStatistic {
		private int score;
		private int nbGood;
		private int nbPerfect;

		private RankStatistic() {
			this.score = 0;
			this.nbGood = 0;
			this.nbPerfect = 0;
		}
	}
}
