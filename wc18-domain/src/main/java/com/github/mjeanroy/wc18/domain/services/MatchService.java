/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.models.Match;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class MatchService {

	private final MatchDao matchDao;

	@Inject
	public MatchService(MatchDao matchDao) {
		this.matchDao = matchDao;
	}

	@Transactional(readOnly = true)
	public Optional<Match> findOne(String id) {
		return matchDao.findOne(id);
	}

	/**
	 * Find all matches.
	 *
	 * @return All matches.
	 */
	@Transactional(readOnly = true)
	public Iterable<Match> findAll() {
		return matchDao.findAll();
	}
}
