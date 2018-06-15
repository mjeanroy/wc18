/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.LeagueBuilder;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

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
	String getOneId() {
		return "cc591102-dedf-432f-b0ea-58459997514c";
	}

	@Override
	void checkOne(League one) {
		assertThat(one.getName()).isEqualTo("League 1");
		assertThat(one.getUsers()).isNotEmpty();
	}

	@Override
	League createOne() {
		return new LeagueBuilder()
			.withName("The Justice League")
			.build();
	}

	@Test
	public void it_should_get_leagues_of_users() {
		User user = findOne(User.class, "10cd4d9f-099c-4491-bfdb-a635b2ffc757");
		List<League> leagues = toList(leagueDao.findByUser(user));
		assertThat(leagues).hasSize(1)
				.extracting(League::getId)
				.contains("cc591102-dedf-432f-b0ea-58459997514c");
	}
}
