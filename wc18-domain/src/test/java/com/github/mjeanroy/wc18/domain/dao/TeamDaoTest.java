package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Team;

import javax.inject.Inject;

public class TeamDaoTest extends AbstractReadOnlyDaoTest<Team> {

	@Inject
	private TeamDao teamDao;

	@Override
	Class<Team> getEntityClass() {
		return Team.class;
	}

	@Override
	AbstractReadOnlyDao<Team> getDao() {
		return teamDao;
	}
}
