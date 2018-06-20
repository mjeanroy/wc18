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

import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.LeagueBuilder;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class LeagueDaoTest extends AbstractCrudDaoTest<League, LeagueDao> {

	@Inject
	private LeagueDao leagueDao;

	@Override
	Class<League> getEntityClass() {
		return League.class;
	}

	@Override
	LeagueDao getDao() {
		return leagueDao;
	}

	@Override
	String getOneId() {
		return "cc591102-dedf-432f-b0ea-58459997514c";
	}

	@Override
	void checkOne(League one) {
		assertThat(one.getName()).isEqualTo("League 1");
		assertThat(one.getUsers()).isNotEmpty();
	}

	@Override
	League createOne() {
		return new LeagueBuilder()
			.withName("The Justice League")
			.build();
	}

	@Test
	public void it_should_get_leagues_of_users() {
		User user = findOne(User.class, "10cd4d9f-099c-4491-bfdb-a635b2ffc757");
		List<League> leagues = toList(leagueDao.findByUser(user));
		assertThat(leagues).hasSize(1)
				.extracting(League::getId)
				.contains("cc591102-dedf-432f-b0ea-58459997514c");
	}
}
