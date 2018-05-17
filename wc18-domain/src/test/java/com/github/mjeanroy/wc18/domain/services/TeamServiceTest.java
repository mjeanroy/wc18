/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.TeamDao;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TeamServiceTest extends AbstractServiceTest {

	@Mock
	private TeamDao teamDao;

	@InjectMocks
	private TeamService teamService;

	@Test
	public void it_should_find_all_matches() {
		List<Team> teams = IntStream.iterate(0, i -> i++)
			.mapToObj(i -> createRandomTeam())
			.limit(10)
			.collect(Collectors.toList());

		initMatchDao(teams);

		Iterable<Team> results = teamService.findAll();

		assertThat(results).isSameAs(teams);
	}

	private void initMatchDao(Collection<Team> teams) {
		when(teamDao.findAll()).thenReturn(teams);
	}

	private Team createRandomTeam() {
		return new TeamBuilder()
			.withRandomId()
			.build();
	}
}
