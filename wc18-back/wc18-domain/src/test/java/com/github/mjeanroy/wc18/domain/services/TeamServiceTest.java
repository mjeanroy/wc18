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

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.TeamDao;
import com.github.mjeanroy.wc18.domain.exceptions.TeamNotFoundException;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractServiceTest;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TeamServiceTest extends AbstractServiceTest {

	private TeamDao teamDao;
	private TeamService teamService;

	@Override
	protected void createService() {
		teamDao = mock(TeamDao.class);
		teamService = new TeamService(teamDao);
	}

	@Test
	void it_should_find_all_matches() {
		List<Team> teams = IntStream.iterate(0, i -> i++)
			.mapToObj(i -> createRandomTeam())
			.limit(10)
			.collect(Collectors.toList());

		initMatchDao(teams);

		Iterable<Team> results = teamService.findAll();

		assertThat(results).isSameAs(teams);
	}

	@Test
	void it_should_find_team() {
		Team team = createRandomTeam();
		String id = team.getId();

		Optional<Team> result = teamService.findOne(id);

		assertThat(result).isPresent().hasValue(team);
		verify(teamDao).findOne(id);
	}

	@Test
	void it_should_find_team_and_return_empty_result_if_team_does_not_exsit() {
		String id = UUID.randomUUID().toString();
		Optional<Team> result = teamService.findOne(id);
		assertThat(result).isNotPresent();
	}

	@Test
	void it_should_find_team_by_id() {
		Team team = createRandomTeam();
		String id = team.getId();

		Team result = teamService.findOneOrFail(id);

		assertThat(result).isSameAs(team);
		verify(teamDao).findOne(id);
	}

	@Test
	void it_should_find_team_and_fail_if_team_does_not_exist() {
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
