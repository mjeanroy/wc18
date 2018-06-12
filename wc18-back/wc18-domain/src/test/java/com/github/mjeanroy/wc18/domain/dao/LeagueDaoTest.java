/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.tests.builders.LeagueBuilder;

import javax.inject.Inject;

public class LeagueDaoTest extends AbstractCrudDaoTest<League, LeagueDao> {

	@Inject
	private LeagueDao leagueDao;

	@Override
	Class<League> getEntityClass() {
		return League.class;
	}

	@Override
	LeagueDao getDao() {
		return leagueDao;
	}

	@Override
	League createOne() {
		return new LeagueBuilder()
			.withName("The Justice League")
			.build();
	}
}
