/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.BetDao;
import com.github.mjeanroy.wc18.domain.dao.MatchDao;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.BetBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RankServiceTest extends AbstractServiceTest {

	@Mock
	private MatchDao matchDao;

	@Mock
	private BetDao betDao;

	@InjectMocks
	private RankService rankService;

	@Test
	public void it_should_compute_ranks() {
		User user1 = new UserBuilder().withRandomId().build();
		User user2 = new UserBuilder().withRandomId().build();
		User user3 = new UserBuilder().withRandomId().build();
		List<User> users = asList(user1, user2, user3);

		Match match1 = new MatchBuilder().withRandomId().withScore(1, 0).build();
		Match match2 = new MatchBuilder().withRandomId().withScore(0, 0).build();
		Match match3 = new MatchBuilder().withRandomId().withScore(0, 2).build();

		List<Bet> bets = asList(
				// User1
				new BetBuilder().withRandomId().withMatch(match1).withUser(user1).withScore(1, 0).build(),
				new BetBuilder().withRandomId().withMatch(match2).withUser(user1).withScore(1, 1).build(),
				new BetBuilder().withRandomId().withMatch(match3).withUser(user1).withScore(3, 0).build(),

				// User2
				new BetBuilder().withRandomId().withMatch(match1).withUser(user2).withScore(2, 0).build(),
				new BetBuilder().withRandomId().withMatch(match2).withUser(user2).withScore(0, 3).build()
		);

		when(matchDao.countByDateLessThanOrderByDate(any(Date.class))).thenReturn(3L);
		when(betDao.findByUserInAndMatchDateLessThan(anyIterable(), any(Date.class))).thenReturn(bets);

		List<Rank> ranks = toList(rankService.getRanks(users));

		assertThat(ranks)
				.hasSameSizeAs(users)
				.isSortedAccordingTo(comparing(Rank::getScore, reverseOrder()))
				.extracting(Rank::getUser, Rank::getScore, Rank::getPercentGood, Rank::getPercentPerfect)
				.contains(
						tuple(user1, 25, 66, 33),
						tuple(user2, 10, 33, 0),
						tuple(user3, 0, 0, 0)
				);

		ArgumentCaptor<Date> argDate1 = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<Date> argDate2 = ArgumentCaptor.forClass(Date.class);

		verify(matchDao).countByDateLessThanOrderByDate(argDate1.capture());
		verify(betDao).findByUserInAndMatchDateLessThan(eq(users), argDate2.capture());

		Date d1 = argDate1.getValue();
		Date d2 = argDate2.getValue();
		assertThat(d1).isEqualTo(d2).isCloseTo(new Date(), 2000);
	}
}
