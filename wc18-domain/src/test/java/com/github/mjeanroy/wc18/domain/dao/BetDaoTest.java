/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.models.User;
import com.github.mjeanroy.wc18.domain.tests.builders.BetBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.ScoreBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.UserBuilder;
import org.assertj.core.api.Condition;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetDaoTest extends AbstractCrudDaoTest<Bet, BetDao> {

	@Inject
	private BetDao betDao;

	@Override
	Class<Bet> getEntityClass() {
		return Bet.class;
	}

	@Override
	BetDao getDao() {
		return betDao;
	}

	@Override
	Bet createOne() {
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
	public void it_should_find_bet_of_given_user() {
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		Iterable<Bet> bets = betDao.findByUser(user);

		assertThat(bets)
			.isNotEmpty()
			.extracting(Bet::getUser)
			.containsOnly(user);
	}

	@Test
	public void it_should_find_a_bet_by_user_and_match() {
		Match match = findOne(Match.class, "4ff9c731-7eba-47bb-91fa-a0c04b2e394e");
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");

		Optional<Bet> optBet = betDao.findOne(user, match);
		assertThat(optBet).isPresent();

		Bet bet = optBet.get();
		assertThat(bet.getId()).isEqualTo("fa066c70-a6ac-418e-927e-add60c1fc272");
		assertThat(bet.getMatch()).isEqualTo(match);
		assertThat(bet.getUser()).isEqualTo(user);
		assertThat(bet.getScore().getScore1()).isEqualTo(2);
		assertThat(bet.getScore().getScore2()).isEqualTo(0);
	}

	@Test
	public void it_should_not_find_non_existing_bet_by_user_and_match() {
		Match match = findOne(Match.class, "ac5d9365-814b-4a61-97bc-2ba59bd1d9a0");
		User user = findOne(User.class, "e31195bd-1d4e-4915-a3dc-ce901f57903f");
		Optional<Bet> optBet = betDao.findOne(user, match);
		assertThat(optBet).isEmpty();
	}
}
