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

import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.exceptions.MatchNotFoundException;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Stage;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.mjeanroy.wc18.domain.tests.commons.ReflectionTestUtils.writeField;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MatchServiceTest extends AbstractServiceTest {

	@Mock
	private MatchDao matchDao;

	@InjectMocks
	private MatchService matchService;

	@Before
	public void initMatchDao() {
		when(matchDao.save(any(Match.class))).thenAnswer(invocation -> {
			Match m = invocation.getArgument(0);
			if (m.getId() == null) {
				writeField(m, "id", UUID.randomUUID().toString());
			}

			return m;
		});
	}

	@Test
	public void it_should_find_all_matches() {
		List<Match> matches = createFixtures();
		when(matchDao.findAllOrderByDate()).thenReturn(matches);

		Iterable<Match> results = matchService.findAll(null);

		assertThat(results).isSameAs(matches);
		verify(matchDao).findAllOrderByDate();
	}

	@Test
	public void it_should_find_match_by_id() {
		Match match = createRandomMatch();
		String id = match.getId();
		Match result = matchService.findOneOrFail(id);

		assertThat(result).isSameAs(match);
		verify(matchDao).findOne(id);
	}

	@Test
	public void it_should_fail_to_find_match_if_it_does_not_exist() {
		String id = UUID.randomUUID().toString();
		assertThatThrownBy(() -> matchService.findOneOrFail(id))
			.isExactlyInstanceOf(MatchNotFoundException.class)
			.hasMessage("Cannot find match '" + id + "'");
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

	@Test
	public void it_should_create_match() {
		Date date = new Date();
		Stage stage = Stage.FINAL;
		Team team1 = new TeamBuilder().withRandomId().build();
		Team team2 = new TeamBuilder().withRandomId().build();

		Match result = matchService.create(date, stage, team1, team2);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getDate()).isEqualTo(date);
		assertThat(result.getStage()).isEqualTo(stage);
		assertThat(result.getTeam1()).isEqualTo(team1);
		assertThat(result.getTeam2()).isEqualTo(team2);
		assertThat(result.getScore()).isNull();
	}

	@Test
	public void it_should_update_match() {
		Match match = createRandomMatch();

		String id = match.getId();
		Date date = new Date();
		Stage stage = Stage.FINAL;
		int score1 = 3;
		int score2 = 0;

		Match result = matchService.update(id, date, stage, score1, score2);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getDate()).isEqualTo(date);
		assertThat(result.getStage()).isEqualTo(stage);
		assertThat(result.getScore()).isNotNull();
		assertThat(result.getScore().getScore1()).isEqualTo(score1);
		assertThat(result.getScore().getScore2()).isEqualTo(score2);
	}

	@Test
	public void it_should_update_match_without_score() {
		Match match = createRandomMatch();

		String id = match.getId();
		Date date = new Date();
		Stage stage = Stage.FINAL;
		Integer score1 = null;
		Integer score2 = null;

		Match result = matchService.update(id, date, stage, score1, score2);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getDate()).isEqualTo(date);
		assertThat(result.getStage()).isEqualTo(stage);
		assertThat(result.getScore()).isNull();
	}

	@Test
	public void it_should_remove_match() {
		Match match = createRandomMatch();
		String id = match.getId();

		matchService.remove(id);

		verify(matchDao).delete(match);
	}

	private List<Match> createFixtures() {
		return IntStream.iterate(0, i -> i++)
			.mapToObj(i -> createRandomMatch())
			.limit(10)
			.collect(Collectors.toList());
	}

	private Match createRandomMatch() {
		Match match = new MatchBuilder()
			.withRandomId()
			.withDate(new Date())
			.withTeam1(new TeamBuilder().withRandomId().withName("team1").build())
			.withTeam2(new TeamBuilder().withRandomId().withName("team2").build())
			.build();

		when(matchDao.findOne(match.getId())).thenReturn(Optional.of(match));

		return match;
	}
}
