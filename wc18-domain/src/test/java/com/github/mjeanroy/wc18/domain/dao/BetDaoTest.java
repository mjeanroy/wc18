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

import javax.inject.Inject;
import java.util.Date;

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
		Match match = new MatchBuilder()
			.withRandomId()
			.withStage(Match.Stage.GROUP)
			.withTeam1(new TeamBuilder()
				.withId("5820fadd-ae19-48d5-b4e5-811b08f58b87")
				.withName("France")
				.withIsoCode("FR")
				.build())
			.withTeam2(new TeamBuilder()
				.withId("a49bed83-afc8-46ee-8d63-768a6b8e52d8")
				.withName("Danemark")
				.withIsoCode("DK")
				.build())
			.build();

		User user = new UserBuilder()
			.withRandomId()
			.withLogin("mickael")
			.withPassword("azerty123")
			.build();

		return new BetBuilder()
			.withDate(new Date())
			.withMatch(match)
			.withUser(user)
			.withScore(new ScoreBuilder()
				.withScore(2, 0)
				.build())
			.build();
	}
}
