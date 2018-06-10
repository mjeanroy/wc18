/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchServiceTest extends AbstractServiceTest {

	@Mock
	private MatchDao matchDao;

	@InjectMocks
	private MatchService matchService;

	@Test
	public void it_should_find_all_matches() {
		List<Match> matches = createFixtures();
		when(matchDao.findAllOrderByDate()).thenReturn(matches);

		Iterable<Match> results = matchService.findAll(null);

		assertThat(results).isSameAs(matches);
		verify(matchDao).findAllOrderByDate();
	}

	@Test
	public void it_should_find_locked_matches() {
		List<Match> matches = createFixtures();
		when(matchDao.findByDateLessThanOrderByDate(any(Date.class))).thenReturn(matches);

		Iterable<Match> results = matchService.findAll(true);

		assertThat(results).isSameAs(matches);

		ArgumentCaptor<Date> argCaptor = ArgumentCaptor.forClass(Date.class);
		verify(matchDao).findByDateLessThanOrderByDate(argCaptor.capture());
		assertThat(argCaptor.getValue()).isCloseTo(new Date(), 1000);
	}

	@Test
	public void it_should_find_non_locked_matches() {
		List<Match> matches = createFixtures();
		when(matchDao.findByDateGreaterThanOrEqualOrderByDate(any(Date.class))).thenReturn(matches);

		Iterable<Match> results = matchService.findAll(false);

		assertThat(results).isSameAs(matches);

		ArgumentCaptor<Date> argCaptor = ArgumentCaptor.forClass(Date.class);
		verify(matchDao).findByDateGreaterThanOrEqualOrderByDate(argCaptor.capture());
		assertThat(argCaptor.getValue()).isCloseTo(new Date(), 1000);
	}

	private static List<Match> createFixtures() {
		return IntStream.iterate(0, i -> i++)
				.mapToObj(i -> createRandomMatch())
				.limit(10)
				.collect(Collectors.toList());
	}

	private static Match createRandomMatch() {
		return new MatchBuilder()
			.withRandomId()
			.withDate(new Date())
			.withTeam1(new TeamBuilder().withRandomId().withName("team1").build())
			.withTeam2(new TeamBuilder().withRandomId().withName("team2").build())
			.build();
	}
}
