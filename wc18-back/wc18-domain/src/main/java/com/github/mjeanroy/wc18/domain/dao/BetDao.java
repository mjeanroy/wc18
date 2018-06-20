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

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static com.github.mjeanroy.wc18.commons.MoreCollections.newHashMap;
import static com.github.mjeanroy.wc18.commons.Tuple.tuple;

@Repository
public class BetDao extends AbstractCrudDao<Bet> {

	/**
	 * Find all bet of given user.
	 *
	 * @param user The user.
	 * @return All bets.
	 */
	public Iterable<Bet> findByUser(User user) {
		String query =
			"SELECT x " +
					"FROM Bet x " +
					"INNER JOIN FETCH x.match m " +
					"INNER JOIN FETCH x.user u " +
					"WHERE u = :user " +
					"ORDER BY m.date";

		return findAll(query, newHashMap(
				tuple("user", user)
		));
	}

	/**
	 * Find all bet of given user.
	 *
	 * @param user The user.
	 * @param date The match date.
	 * @return All bets.
	 */
	public Iterable<Bet> findByUserAndMatchDateLessThan(User user, Date date) {
		String query =
				"SELECT x " +
						"FROM Bet x " +
						"INNER JOIN FETCH x.match m " +
						"INNER JOIN FETCH x.user u " +
						"WHERE u = :user " +
						"AND m.date < :date " +
						"ORDER BY m.date ASC";

		return findAll(query, new HashMap<String, Object>() {{
				put("user", user);
				put("date", date);
		}});
	}

	/**
	 * Find all bet of given user.
	 *
	 * @param user The user.
	 * @param date The match date.
	 * @return All bets.
	 */
	public Iterable<Bet> findByUserAndMatchDateGreaterThanOrEqual(User user, Date date) {
		String query =
				"SELECT x " +
						"FROM Bet x " +
						"INNER JOIN FETCH x.match m " +
						"INNER JOIN FETCH x.user u " +
						"WHERE u = :user " +
						"AND m.date >= :date " +
						"ORDER BY m.date ASC";

		return findAll(query, newHashMap(
			tuple("user", user),
			tuple("date", date)
		));
	}

	/**
	 * Find bet of given match/user.
	 *
	 * @param user The user.
	 * @param match The match.
	 * @return The bet, if it exists.
	 */
	public Optional<Bet> findOne(User user, Match match) {
		String query =
			"SELECT x " +
					"FROM Bet x " +
					"INNER JOIN FETCH x.match m " +
					"INNER JOIN FETCH x.user u " +
					"WHERE u = :user " +
					"AND m = :match";

		return findOne(query, newHashMap(
				tuple("user", user),
				tuple("match", match)
		));
	}

	/**
	 * Find all bet of given users.
	 *
	 * @param users The users.
	 * @param date The match date.
	 * @return All bets.
	 */
	public Iterable<Bet> findByUserInAndMatchDateLessThan(Iterable<User> users, Date date) {
		String query =
				"SELECT x " +
						"FROM Bet x " +
						"INNER JOIN FETCH x.match m " +
						"INNER JOIN FETCH x.user u " +
						"WHERE u IN (:users) " +
						"AND m.date < :date " +
						"ORDER BY m.date ASC";

		return findAll(query, new HashMap<String, Object>() {{
			put("users", users);
			put("date", date);
		}});
	}

	/**
	 * Find all bet of given users and given match.
	 *
	 * @param users The users.
	 * @param match The match.
	 * @return All bets.
	 */
	public Iterable<Bet> findByUserInAndMatch(Iterable<User> users, Match match) {
		String query =
				"SELECT x " +
						"FROM Bet x " +
						"INNER JOIN FETCH x.match m " +
						"INNER JOIN FETCH x.user u " +
						"WHERE u IN (:users) " +
						"AND m = :match " +
						"ORDER BY u.login";

		return findAll(query, new HashMap<String, Object>() {{
			put("users", users);
			put("match", match);
		}});
	}
}
