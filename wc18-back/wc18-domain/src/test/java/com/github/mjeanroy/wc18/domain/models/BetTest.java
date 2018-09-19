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

package com.github.mjeanroy.wc18.domain.models;

import com.github.mjeanroy.wc18.domain.tests.builders.BetBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class BetTest {

	@Test
	void it_should_return_zero_point_if_match_is_not_played_yet() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenNotPlayedMatch())
			.build();

		assertThat(bet.getPoint()).isEqualTo(0);
	}

	@Test
	void it_should_return_zero_point_if_bet_is_loosed() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(Stage.GROUP, 2, 0))
			.withScore(0, 2)
			.build();

		assertThat(bet.getPoint()).isEqualTo(0);
	}

	@Test
	void it_should_return_win_point_if_bet_is_won() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(Stage.GROUP, 2, 0))
			.withScore(1, 0)
			.build();

		assertThat(bet.getPoint()).isEqualTo(10);
	}

	@Test
	void it_should_return_win_point_if_bet_is_won_with_match_equality() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(Stage.GROUP, 2, 2))
			.withScore(1, 1)
			.build();

		assertThat(bet.getPoint()).isEqualTo(10);
	}

	@Test
	void it_should_return_perfect_point_if_bet_is_perfect() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(Stage.GROUP, 2, 0))
			.withScore(2, 0)
			.build();

		assertThat(bet.getPoint()).isEqualTo(15);
	}

	@Test
	void it_should_return_perfect_point_if_bet_is_perfect_with_match_equality() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(Stage.GROUP, 1, 1))
			.withScore(1, 1)
			.build();

		assertThat(bet.getPoint()).isEqualTo(15);
	}

	@Test
	void it_should_apply_coefficient_per_stage() {
		Bet bet = new BetBuilder()
			.withRandomId()
			.withMatch(givenPlayedMatch(Stage.FINAL, 1, 1))
			.withScore(1, 1)
			.build();

		assertThat(bet.getPoint()).isEqualTo(150);
	}

	private Match givenPlayedMatch(Stage stage, int score1, int score2) {
		return new MatchBuilder()
			.withRandomId()
			.withDate(new Date())
			.withStage(stage)
			.withScore(score1, score2)
			.build();
	}

	private Match givenNotPlayedMatch() {
		return new MatchBuilder()
			.withRandomId()
			.withDate(new Date())
			.withStage(Stage.GROUP)
			.build();
	}
}
