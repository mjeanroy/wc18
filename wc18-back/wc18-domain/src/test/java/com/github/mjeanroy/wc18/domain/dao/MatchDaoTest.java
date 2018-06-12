/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
}
