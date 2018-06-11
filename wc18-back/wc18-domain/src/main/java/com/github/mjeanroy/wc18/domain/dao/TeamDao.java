/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Team;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDao extends AbstractReadOnlyDao<Team> {

	/**
	 * Find all teams ordered by name.
	 *
	 * @return All teams.
	 */
	public Iterable<Team> findAllOrderByName() {
		String jpql =
			"SELECT t " +
				"FROM Team t " +
				"ORDER BY t.name";

		return findAll(jpql);
	}
}
