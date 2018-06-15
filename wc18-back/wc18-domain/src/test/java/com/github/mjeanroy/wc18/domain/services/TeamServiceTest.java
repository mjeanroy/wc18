/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.TeamDao;
import com.github.mjeanroy.wc18.domain.exceptions.TeamNotFoundException;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
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

	@Test
	public void it_should_find_team() {
		Team team = createRandomTeam();
		String id = team.getId();

		Optional<Team> result = teamService.findOne(id);

		assertThat(result).isPresent().hasValue(team);
		verify(teamDao).findOne(id);
	}

	@Test
	public void it_should_find_team_and_return_empty_result_if_team_does_not_exsit() {
		String id = UUID.randomUUID().toString();
		Optional<Team> result = teamService.findOne(id);
		assertThat(result).isNotPresent();
	}

	@Test
	public void it_should_find_team_by_id() {
		Team team = createRandomTeam();
		String id = team.getId();

		Team result = teamService.findOneOrFail(id);

		assertThat(result).isSameAs(team);
		verify(teamDao).findOne(id);
	}

	@Test
	public void it_should_find_team_and_fail_if_team_does_not_exist() {
		String id = UUID.randomUUID().toString();
		assertThatThrownBy(() -> teamService.findOneOrFail(id))
				.isExactlyInstanceOf(TeamNotFoundException.class)
				.hasMessage("Cannot find team '" + id + "'");
	}

	private void initMatchDao(Collection<Team> teams) {
		when(teamDao.findAll()).thenReturn(teams);
		when(teamDao.findAllOrderByName()).thenReturn(teams);
	}

	private Team createRandomTeam() {
		Team team = new TeamBuilder()
			.withRandomId()
			.withName("team")
			.build();

		when(teamDao.findOne(team.getId())).thenReturn(Optional.of(team));

		return team;
	}
}
