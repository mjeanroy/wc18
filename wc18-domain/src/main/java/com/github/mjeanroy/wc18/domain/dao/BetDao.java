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

import java.util.Optional;

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
			"WHERE x.user = :user " +
			"ORDER BY x.match.date";

		return findAll(getEntityManager()
			.createQuery(query)
			.setParameter("user", user));
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
				"WHERE x.user = :user " +
				"AND x.match = :match";

		return findOne(getEntityManager()
			.createQuery(query)
			.setParameter("user", user)
			.setParameter("match", match));
	}
}
