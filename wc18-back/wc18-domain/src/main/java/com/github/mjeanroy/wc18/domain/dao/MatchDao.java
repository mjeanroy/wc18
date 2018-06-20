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
