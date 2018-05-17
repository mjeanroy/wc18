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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MatchServiceTest extends AbstractServiceTest {

	@Mock
	private MatchDao matchDao;

	@InjectMocks
	private MatchService matchService;

	@Test
	public void it_should_find_all_matches() {
		List<Match> matches = IntStream.iterate(0, i -> i++)
			.mapToObj(i -> createRandomMatch())
			.limit(10)
			.collect(Collectors.toList());

		initMatchDao(matches);

		Iterable<Match> results = matchService.findAll();

		assertThat(results).isSameAs(matches);
	}

	private void initMatchDao(Collection<Match> matches) {
		when(matchDao.findAll()).thenReturn(matches);
	}

	private Match createRandomMatch() {
		return new MatchBuilder()
			.withRandomId()
			.withTeam1(new TeamBuilder().withRandomId().build())
			.withTeam2(new TeamBuilder().withRandomId().build())
			.build();
	}
}
