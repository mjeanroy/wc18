/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Repository;

import static com.github.mjeanroy.wc18.commons.MoreCollections.newHashMap;
import static com.github.mjeanroy.wc18.commons.Tuple.tuple;

@Repository
public class LeagueDao extends AbstractCrudDao<League> {

	@Override
	public Iterable<League> findAll() {
		String query =
				"SELECT DISTINCT league " +
						"FROM League league " +
						"LEFT OUTER JOIN FETCH league.users " +
						"ORDER BY league.name ";

		return findAll(query);
	}

	public Iterable<League> findByUser(User user) {
		String query =
				"SELECT DISTINCT league " +
						"FROM League league " +
						"LEFT OUTER JOIN FETCH league.users users " +
						"WHERE users = :user " +
						"ORDER BY league.name ";

		return findAll(query, newHashMap(
				tuple("user", user)
		));
	}
}
