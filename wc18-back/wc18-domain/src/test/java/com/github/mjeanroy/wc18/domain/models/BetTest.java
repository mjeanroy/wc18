/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import com.github.mjeanroy.wc18.domain.tests.builders.BetBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class BetTest {

	@Test
	public void it_should_return_zero_point_if_match_is_not_played_yet() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenNotPlayedMatch())
			.build();

		assertThat(bet.getPoint()).isEqualTo(0);
	}

	@Test
	public void it_should_return_zero_point_if_bet_is_loosed() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(2, 0))
			.withScore(0, 2)
			.build();

		assertThat(bet.getPoint()).isEqualTo(0);
	}

	@Test
	public void it_should_return_win_point_if_bet_is_won() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(2, 0))
			.withScore(1, 0)
			.build();

		assertThat(bet.getPoint()).isEqualTo(10);
	}

	@Test
	public void it_should_return_win_point_if_bet_is_won_with_match_equality() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(2, 2))
			.withScore(1, 1)
			.build();

		assertThat(bet.getPoint()).isEqualTo(10);
	}

	@Test
	public void it_should_return_perfect_point_if_bet_is_perfect() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(2, 0))
			.withScore(2, 0)
			.build();

		assertThat(bet.getPoint()).isEqualTo(15);
	}

	@Test
	public void it_should_return_perfect_point_if_bet_is_perfect_with_match_equality() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(1, 1))
			.withScore(1, 1)
			.build();

		assertThat(bet.getPoint()).isEqualTo(15);
	}

	private Match givenPlayedMatch(int score1, int score2) {
		return new MatchBuilder()
			.withRandomId()
			.withDate(new Date())
			.withScore(score1, score2)
			.build();
	}

	private Match givenNotPlayedMatch() {
		return new MatchBuilder()
			.withRandomId()
			.withDate(new Date())
			.build();
	}
}
