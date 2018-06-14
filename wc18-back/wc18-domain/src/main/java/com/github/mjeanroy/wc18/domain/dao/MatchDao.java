/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Match;
import org.springframework.stereotype.Repository;

import java.util.Date;

import static com.github.mjeanroy.wc18.commons.MoreCollections.newHashMap;
import static com.github.mjeanroy.wc18.commons.Tuple.tuple;

@Repository
public class MatchDao extends AbstractCrudDao<Match> {

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

		return findAll(jpql, newHashMap(
				tuple("date", date)
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

		return findAll(jpql, newHashMap(
				tuple("date", date)
		));
	}

	public long countByDateLessThanOrderByDate(Date date) {
		String jpql =
				"SELECT COUNT(m) " +
						"FROM Match m " +
						"WHERE m.date < :date ";

		return count(jpql, newHashMap(
				tuple("date", date)
		));
	}
}
