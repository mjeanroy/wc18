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

import com.github.mjeanroy.wc18.domain.dao.LeagueDao;
import com.github.mjeanroy.wc18.domain.exceptions.LeagueNotFoundException;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.LeagueBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.RankBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.mjeanroy.wc18.domain.tests.commons.ReflectionTestUtils.writeField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class LeagueServiceTest extends AbstractServiceTest {

	@Mock
	private LeagueDao leagueDao;

	@Mock
	private RankService rankService;

	@InjectMocks
	private LeagueService leagueService;

	@Before
	public void initLeagueDao() {
		when(leagueDao.save(any(League.class))).thenAnswer(invocation -> {
			League input = invocation.getArgument(0);
			if (input.getId() == null) {
				writeField(input, "id", UUID.randomUUID().toString());
			}

			return input;
		});
	}

	@Test
	public void it_should_find_all_leagues() {
		List<League> leagues = createRandomLeagues();
		Iterable<League> results = leagueService.findAll();

		assertThat(results).isSameAs(leagues);
		verify(leagueDao).findAll();
		verifyNoMoreInteractions(leagueDao);
	}

	@Test
	public void it_should_find_leagues_by_user() {
		User user = new UserBuilder().withRandomId().build();
		List<League> leagues = createRandomLeagues();

		Iterable<League> results = leagueService.findByUser(user);

		assertThat(results).isSameAs(leagues);
		verify(leagueDao).findByUser(user);
		verifyNoMoreInteractions(leagueDao);
	}

	@Test
	public void it_should_find_league() {
		League league = createLeague();
		String id = league.getId();

		League result = leagueService.findOneOrFail(id);

		assertThat(result).isSameAs(league);
		verify(leagueDao).findOne(id);
	}

	@Test
	public void it_should_fail_if_league_does_not_exist() {
		String id = UUID.randomUUID().toString();

		assertThatThrownBy(() -> leagueService.findOneOrFail(id))
			.isExactlyInstanceOf(LeagueNotFoundException.class)
			.hasMessage("Cannot find league '" + id + "'");
	}

	@Test
	public void it_should_find_ranks() {
		User user1 = createUser();
		User user2 = createUser();
		League league = createLeague(user1, user2);
		List<Rank> ranks = createRanks(user1, user2);

		Iterable<Rank> results = leagueService.findRanks(league);

		assertThat(results).isSameAs(ranks);
		verify(rankService).getRanks(league.getUsers());
		verifyNoMoreInteractions(rankService);
	}

	@Test
	public void it_should_create_league() {
		String name = UUID.randomUUID().toString();
		League league = leagueService.create(name);

		assertThat(league).isNotNull();
		assertThat(league.getId()).isNotNull();
		assertThat(league.getName()).isEqualTo(name);
		assertThat(league.getUsers()).isEmpty();

		verify(leagueDao).save(league);
		verifyNoMoreInteractions(leagueDao);
	}

	@Test
	public void it_should_remove_league() {
		League league = createLeague();
		leagueService.remove(league.getId());

		verify(leagueDao).findOne(league.getId());
		verify(leagueDao).delete(league);
		verifyNoMoreInteractions(leagueDao);
	}

	@Test
	public void it_should_add_user_to_league() {
		User user = createUser();
		League league = createLeague();

		leagueService.addUser(league, user);

		assertThat(league.getUsers()).hasSize(1).contains(user);
		verify(leagueDao).save(league);
		verifyNoMoreInteractions(leagueDao);
	}

	private List<League> createRandomLeagues() {
		List<League> leagues = IntStream.iterate(0, i -> i++)
			.mapToObj(i -> createLeague())
			.limit(10)
			.collect(Collectors.toList());

		when(leagueDao.findAll()).thenReturn(leagues);
		when(leagueDao.findByUser(any(User.class))).thenReturn(leagues);

		return leagues;
	}

	private League createLeague(User... users) {
		League league = new LeagueBuilder()
			.withRandomId()
			.withName("League -- " + UUID.randomUUID().toString())
			.withUsers(users)
			.build();

		when(leagueDao.findOne(league.getId())).thenReturn(Optional.of(league));

		return league;
	}

	private User createUser() {
		return new UserBuilder().withRandomId().build();
	}

	private List<Rank> createRanks(User... users) {
		List<Rank> ranks = Arrays.stream(users)
			.map(user -> new RankBuilder().withUser(user).build())
			.collect(Collectors.toList());

		when(rankService.getRanks(anyIterable())).thenReturn(ranks);

		return ranks;
	}
}
