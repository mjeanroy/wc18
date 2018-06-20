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

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Match.Stage;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import org.assertj.core.api.Condition;
import org.junit.Test;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class MatchDaoTest extends AbstractCrudDaoTest<Match, MatchDao> {

	@Inject
	private MatchDao matchDao;

	@Override
	Class<Match> getEntityClass() {
		return Match.class;
	}

	@Override
	MatchDao getDao() {
		return matchDao;
	}

	@Override
	String getOneId() {
		return "251643b1-5a15-4cac-8f84-6cf0153a2480";
	}

	@Override
	void checkOne(Match one) {
		assertThat(one.getStage()).isEqualTo(Stage.GROUP);
		assertThat(one.getDate()).hasYear(2018).hasMonth(6).hasDayOfMonth(21).hasHourOfDay(15).hasMinute(0).hasSecond(0).hasMillisecond(0);
		assertThat(one.getTeam1()).isNotNull();
		assertThat(one.getTeam2()).isNotNull();
		assertThat(one.getScore()).isNull();
	}

	@Override
	Match createOne() {
		Team team1 = findOne(Team.class, "5820fadd-ae19-48d5-b4e5-811b08f58b87");
		Team team2 = findOne(Team.class, "e9c4e714-5b4b-4e2d-a896-9f043c295869");
		return new MatchBuilder()
			.withDate(new Date())
			.withStage(Stage.FINAL)
			.withTeam1(team1)
			.withTeam2(team2)
			.build();
	}

	@Test
	public void it_should_find_all_matches_ordered_by_date() {
		List<Match> matches = toList(matchDao.findAllOrderByDate());

		assertThat(matches)
			.isNotEmpty()
			.isSortedAccordingTo(Comparator.comparing(Match::getDate));
	}

	@Test
	public void it_should_get_matches_with_date_less_than_ordered_by_date() {
		Date date = Date.from(LocalDateTime.parse("2018-06-20T12:00:00.000").atZone(ZoneId.systemDefault()).toInstant());

		List<Match> matches = toList(matchDao.findByDateLessThanOrderByDate(date));

		assertThat(matches)
			.isNotEmpty()
			.isSortedAccordingTo(Comparator.comparing(Match::getDate))
			.are(new Condition<Match>() {
				@Override
				public boolean matches(Match value) {
					return value.getDate().getTime() < date.getTime();
				}
			});
	}

	@Test
	public void it_should_get_matches_with_date_greater_than_ordered_by_date() {
		Date date = Date.from(LocalDateTime.parse("2018-06-20T12:00:00.000").atZone(ZoneId.systemDefault()).toInstant());

		List<Match> matches = toList(matchDao.findByDateGreaterThanOrEqualOrderByDate(date));

		assertThat(matches)
			.isNotEmpty()
			.isSortedAccordingTo(Comparator.comparing(Match::getDate))
			.are(new Condition<Match>() {
				@Override
				public boolean matches(Match value) {
					return value.getDate().getTime() >= date.getTime();
				}
			});
	}

	@Test
	public void it_should_count_by_match_date_less_than() {
		Date date = Date.from(LocalDateTime.parse("2018-06-20T12:00:00.000").atZone(ZoneId.systemDefault()).toInstant());
		long count = matchDao.countByDateLessThanOrderByDate(date);
		assertThat(count).isEqualTo(17L);
	}
}
