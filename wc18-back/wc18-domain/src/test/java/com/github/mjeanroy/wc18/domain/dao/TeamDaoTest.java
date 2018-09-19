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

import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractReadOnlyDaoTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Comparator;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

class TeamDaoTest extends AbstractReadOnlyDaoTest<Team, TeamDao> {

	@Inject
	private TeamDao teamDao;

	@Override
	protected Class<Team> getEntityClass() {
		return Team.class;
	}

	@Override
	protected TeamDao getDao() {
		return teamDao;
	}

	@Override
	protected String getOneId() {
		return "5820fadd-ae19-48d5-b4e5-811b08f58b87";
	}

	@Override
	protected void checkOne(Team one) {
		assertThat(one.getIsoCode()).isEqualTo("FR");
		assertThat(one.getName()).isEqualTo("France");
	}

	@Test
	void it_should_find_all_teams_ordered_by_name() {
		Iterable<Team> teams = teamDao.findAllOrderByName();
		assertThat(teams).isNotEmpty();
		assertThat(toList(teams)).isSortedAccordingTo(Comparator.comparing(Team::getName));
	}
}
