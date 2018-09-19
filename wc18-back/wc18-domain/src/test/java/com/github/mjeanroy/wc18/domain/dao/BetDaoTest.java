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

import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.BetBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.ScoreBuilder;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractCrudDaoTest;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class BetDaoTest extends AbstractCrudDaoTest<Bet, BetDao> {

	@Inject
	private BetDao betDao;

	@Override
	protected Class<Bet> getEntityClass() {
		return Bet.class;
	}

	@Override
	protected BetDao getDao() {
		return betDao;
	}

	@Override
	protected String getOneId() {
		return "8edec0c9-2f71-4ae9-beb5-ae16024bc3d1";
	}

	@Override
	protected void checkOne(Bet one) {
		assertThat(one.getMatch()).isNotNull();
		assertThat(one.getUser()).isNotNull();
		assertThat(one.getDate()).hasYear(2015).hasMonth(5).hasDayOfMonth(16).hasHourOfDay(0).hasMinute(0).hasSecond(0).hasMillisecond(0);
		assertThat(one.getScore()).isNotNull();
		assertThat(one.getScore().getScore1()).isEqualTo(2);
		assertThat(one.getScore().getScore2()).isEqualTo(0);
	}

	@Override
	protected Bet createOne() {
		Match match = findOne(Match.class, "251643b1-5a15-4cac-8f84-6cf0153a2480");
		User user = findOne(User.class, "10cd4d9f-099c-4491-bfdb-a635b2ffc757");
		return new BetBuilder()
			.withDate(new Date())
			.withMatch(match)
			.withUser(user)
			.withScore(new ScoreBuilder().withScore(2, 0).build())
			.build();
	}

	@Test
	void it_should_find_bet_of_given_user() {
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		List<Bet> bets = toList(betDao.findByUser(user));
		assertBetsOfGivenUser(bets, user);
	}

	@Test
	void it_should_find_bet_of_given_user_with_match_date_less_than() {
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		Date date = Date.from(LocalDateTime.parse("2018-06-20T12:00:00.000").atZone(ZoneId.systemDefault()).toInstant());

		List<Bet> bets = toList(betDao.findByUserAndMatchDateLessThan(user, date));

		assertThat(bets)
			.isNotEmpty()
			.are(new Condition<Bet>() {
				@Override
				public boolean matches(Bet value) {
					return value.getMatch().getDate().getTime() < date.getTime();
				}
			});

		assertBetsOfGivenUser(bets, user);
	}

	@Test
	void it_should_find_bet_of_given_user_with_match_date_greater_than() {
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		Date date = Date.from(LocalDateTime.parse("2018-06-20T12:00:00.000").atZone(ZoneId.systemDefault()).toInstant());

		List<Bet> bets = toList(betDao.findByUserAndMatchDateGreaterThanOrEqual(user, date));

		assertThat(bets)
			.isNotEmpty()
			.are(new Condition<Bet>() {
				@Override
				public boolean matches(Bet value) {
					return value.getMatch().getDate().getTime() >= date.getTime();
				}
			});

		assertBetsOfGivenUser(bets, user);
	}

	private void assertBetsOfGivenUser(List<Bet> bets, User user) {
		assertThat(bets).extracting(Bet::getUser).containsOnly(user);
	}

	@Test
	void it_should_find_a_bet_by_user_and_match() {
		Match match = findOne(Match.class, "4ff9c731-7eba-47bb-91fa-a0c04b2e394e");
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");

		Optional<Bet> optBet = betDao.findOne(user, match);
		assertThat(optBet).isPresent();

		Bet bet = optBet.orElseThrow(AssertionError::new);
		assertThat(bet.getId()).isEqualTo("fa066c70-a6ac-418e-927e-add60c1fc272");
		assertThat(bet.getMatch()).isEqualTo(match);
		assertThat(bet.getUser()).isEqualTo(user);
		assertThat(bet.getScore().getScore1()).isEqualTo(2);
		assertThat(bet.getScore().getScore2()).isEqualTo(0);
	}

	@Test
	void it_should_not_find_non_existing_bet_by_user_and_match() {
		Match match = findOne(Match.class, "ac5d9365-814b-4a61-97bc-2ba59bd1d9a0");
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		Optional<Bet> optBet = betDao.findOne(user, match);
		assertThat(optBet).isEmpty();
	}

	@Test
	void it_should_get_bets_by_match_and_user_in() {
		User u1 = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		User u2 = findOne(User.class, "10cd4d9f-099c-4491-bfdb-a635b2ffc757");
		List<User> users = asList(u1, u2);
		Date date = Date.from(LocalDateTime.parse("2018-06-16T12:00:00.000").atZone(ZoneId.systemDefault()).toInstant());

		List<Bet> bets = toList(betDao.findByUserInAndMatchDateLessThan(users, date));

		assertThat(bets)
			.isNotEmpty()
			.extracting(Bet::getId)
			.contains(
				"ec1eb37b-bdb0-44f4-980f-1ae8a062e196",
				"fa066c70-a6ac-418e-927e-add60c1fc272"
			);
	}
}
