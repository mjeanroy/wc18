/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Match;

import javax.inject.Inject;

public class MatchDaoTest extends AbstractReadOnlyDaoTest<Match> {

	@Inject
	private MatchDao matchDao;

	@Override
	Class<Match> getEntityClass() {
		return Match.class;
	}

	@Override
	AbstractReadOnlyDao<Match> getDao() {
		return matchDao;
	}
}
