/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Match;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static java.util.Collections.singletonMap;

@Repository
public class MatchDao extends AbstractReadOnlyDao<Match> {

	public Iterable<Match> findAllOrderByDate() {
		String jpql =
				"SELECT m " +
						"FROM Match m " +
						"INNER JOIN FETCH m.team1 " +
						"INNER JOIN FETCH m.team2 " +
						"ORDER BY m.date ASC, m.team1.name ASC";

		return findAll(jpql);
	}

	public Iterable<Match> findByDateLessThanOrderByDate(Date date) {
		String jpql =
			"SELECT m " +
				"FROM Match m " +
				"INNER JOIN FETCH m.team1 " +
				"INNER JOIN FETCH m.team2 " +
				"WHERE m.date < :date " +
				"ORDER BY m.date ASC, m.team1.name ASC";

		return findAll(jpql, singletonMap(
				"date", date
		));
	}

	public Iterable<Match> findByDateGreaterThanOrEqualOrderByDate(Date date) {
		String jpql =
				"SELECT m " +
						"FROM Match m " +
						"INNER JOIN FETCH m.team1 " +
						"INNER JOIN FETCH m.team2 " +
						"WHERE m.date >= :date " +
						"ORDER BY m.date ASC, m.team1.name ASC";

		return findAll(jpql, singletonMap(
				"date", date
		));
	}
}
