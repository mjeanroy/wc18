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

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.exceptions.MatchLockedException;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.BetBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractServiceTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

class BetServiceTest extends AbstractServiceTest {

	private BetDao betDao;
	private BetService betService;

	private TimeZone defaultTz;

	@Override
	protected void createService() {
		betDao = mock(BetDao.class);
		betService = new BetService(betDao);
	}

	@BeforeEach
	void initUtcTimeZone() {
		defaultTz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@AfterEach
	void restoreDefaultTimeZone() {
		TimeZone.setDefault(defaultTz);
	}

	@BeforeEach
	void initBetDao() {
		when(betDao.save(any(Bet.class))).thenAnswer(invocationOnMock ->
			invocationOnMock.getArgument(0)
		);
	}

	@Test
	void it_should_get_all_bets_of_given_user() {
		User user = new UserBuilder().withRandomId().build();
		List<Bet> bets = createFixtures(user);
		when(betDao.findByUser(user)).thenReturn(bets);

		Iterable<Bet> results = betService.findByUser(user, null);

		assertThat(results).isSameAs(bets);
		verify(betDao).findByUser(user);
	}

	@Test
	void it_should_get_all_non_locked_bets_of_given_user() {
		User user = new UserBuilder().withRandomId().build();
		List<Bet> bets = createFixtures(user);
		when(betDao.findByUserAndMatchDateGreaterThanOrEqual(eq(user), any(Date.class))).thenReturn(bets);

		Iterable<Bet> results = betService.findByUser(user, false);

		assertThat(results).isSameAs(bets);

		ArgumentCaptor<Date> argCaptor = ArgumentCaptor.forClass(Date.class);
		verify(betDao).findByUserAndMatchDateGreaterThanOrEqual(eq(user), argCaptor.capture());
		assertThat(argCaptor.getValue()).isCloseTo(new Date(), 1000);
	}

	@Test
	void it_should_get_all_locked_bets_of_given_user() {
		User user = new UserBuilder().withRandomId().build();
		List<Bet> bets = createFixtures(user);
		when(betDao.findByUserAndMatchDateLessThan(eq(user), any(Date.class))).thenReturn(bets);

		Iterable<Bet> results = betService.findByUser(user, true);

		assertThat(results).isSameAs(bets);

		ArgumentCaptor<Date> argCaptor = ArgumentCaptor.forClass(Date.class);
		verify(betDao).findByUserAndMatchDateLessThan(eq(user), argCaptor.capture());
		assertThat(argCaptor.getValue()).isCloseTo(new Date(), 1000);
	}

	@Test
	void it_should_save_bet() {
		User user = new UserBuilder().withRandomId().build();
		Match match = new MatchBuilder()
			.withRandomId()
			.withDate(Date.from(LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.UTC)))
			.build();

		int score1 = 2;
		int score2 = 0;

		Bet result = betService.save(user, match, score1, score2, true);

		assertThat(result).isNotNull();
		assertThat(result.getMatch()).isEqualTo(match);
		assertThat(result.getUser()).isEqualTo(user);
		assertThat(result.getDate()).isCloseTo(new Date(), 2000);
		assertThat(result.getScore().getScore1()).isEqualTo(score1);
		assertThat(result.getScore().getScore2()).isEqualTo(score2);

		verify(betDao).save(result);
	}

	@Test
	void it_should_update_bet() {
		User user = new UserBuilder().withRandomId().build();
		Match match = new MatchBuilder()
			.withRandomId()
			.withDate(Date.from(LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.UTC)))
			.build();

		Bet bet = createBet(user, match, 1, 2);

		int newScore1 = 2;
		int newScore2 = 0;

		Bet result = betService.save(user, match, newScore1, newScore2, true);

		assertThat(result).isNotNull().isSameAs(bet);
		assertThat(result.getMatch()).isEqualTo(match);
		assertThat(result.getUser()).isEqualTo(user);
		assertThat(result.getDate()).isCloseTo(new Date(), 2000);
		assertThat(result.getScore().getScore1()).isEqualTo(newScore1);
		assertThat(result.getScore().getScore2()).isEqualTo(newScore2);

		verify(betDao).save(result);
	}

	@Test
	void it_should_fail_if_match_is_locked() {
		User user = new UserBuilder().withRandomId().build();
		Match match = new MatchBuilder()
			.withRandomId()
			.withDate(Date.from(LocalDateTime.now().minusHours(1).toInstant(ZoneOffset.UTC)))
			.build();

		int newScore1 = 2;
		int newScore2 = 0;

		assertThatThrownBy(() -> betService.save(user, match, newScore1, newScore2, true))
			.isExactlyInstanceOf(MatchLockedException.class)
			.hasMessage("Match '" + match.getId() + "' is locked for bet");

		verifyZeroInteractions(betDao);
	}

	private List<Bet> createFixtures(User user) {
		Bet bet1 = createBet(user, new MatchBuilder().withRandomId().build(), 1, 0);
		Bet bet2 = createBet(user, new MatchBuilder().withRandomId().build(), 0, 0);
		return asList(bet1, bet2);
	}

	private Bet createBet(User user, Match match, int score1, int score2) {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withDate(Date.from(LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC)))
			.withUser(user)
			.withMatch(match)
			.withScore(score1, score2)
			.build();

		when(betDao.findOne(user, match)).thenReturn(Optional.of(bet));

		return bet;
	}
}
