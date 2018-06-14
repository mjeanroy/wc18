/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.League;
import org.springframework.stereotype.Repository;

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
}
