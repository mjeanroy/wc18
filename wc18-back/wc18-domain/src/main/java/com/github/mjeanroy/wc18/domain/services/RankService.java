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
import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

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
	Iterable<Rank> getRanks(Iterable<User> users) {
		Map<User, RankStatistic> scores = new HashMap<>();

		// Add everybody with a score of zero.
		for (User user : users) {
			scores.put(user, new RankStatistic());
		}

		Date now = new Date();

		// Now, get the result of each bet.
		// In case of performance problem, just store the score in database.
		final long nbMatches = matchDao.countByDateLessThanOrderByDate(now);
		final Iterable<Bet> bets = betDao.findByUserInAndMatchDateLessThan(users, now);

		for (Bet bet : bets) {
			User user = bet.getUser();
			RankStatistic statistic = scores.get(user);

			statistic.score += bet.getPoint();

			Bet.Result result = bet.getResult();
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
			int percentGood = toIntExact(statistic.nbGood * 100 / nbMatches);
			int percentPerfect = toIntExact(statistic.nbPerfect * 100 / nbMatches);
			ranks.add(new Rank(user, score, percentGood, percentPerfect));
		}

		ranks.sort(comparing(Rank::getScore, reverseOrder()));

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
