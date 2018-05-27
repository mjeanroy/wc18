package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Team;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Comparator;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class TeamDaoTest extends AbstractReadOnlyDaoTest<Team, TeamDao> {

	@Inject
	private TeamDao teamDao;

	@Override
	Class<Team> getEntityClass() {
		return Team.class;
	}

	@Override
	TeamDao getDao() {
		return teamDao;
	}

	@Test
	public void it_should_find_all_teams_ordered_by_name() {
		Iterable<Team> teams = teamDao.findAllOrderByName();
		assertThat(teams).isNotEmpty();
		assertThat(toList(teams)).isSortedAccordingTo(Comparator.comparing(Team::getName));
	}
}
