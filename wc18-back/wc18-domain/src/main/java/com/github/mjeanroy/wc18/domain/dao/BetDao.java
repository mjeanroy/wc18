/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
}
