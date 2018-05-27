/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Match;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Comparator;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class MatchDaoTest extends AbstractReadOnlyDaoTest<Match, MatchDao> {

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

	@Test
	public void it_should_get_all_matches_ordered_by_date() {
		Iterable<Match> matches = matchDao.findAllOrderByDate();
		assertThat(matches).isNotEmpty();
		assertThat(toList(matches)).isSortedAccordingTo(Comparator.comparing(Match::getDate));
	}
}
