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

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.models.User;

import java.util.Date;
import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;

/**
 * Builder for {@link Bet} instances.
 */
public class BetBuilder {

	/**
	 * The bet id.
	 * @see Bet#id
	 */
	private String id;

	/**
	 * The bet date.
	 * @see Bet#date
	 */
	private Date date;

	/**
	 * The bet user.
	 * @see Bet#user
	 */
	private User user;

	/**
	 * The bet match.
	 * @see Bet#match
	 */
	private Match match;

	/**
	 * The bet score.
	 * @see Bet#score
	 */
	private Score score;

	/**
	 * Set {@link #id} with a random id.
	 *
	 * @return The current builder.
	 */
	public BetBuilder withRandomId() {
		this.id = UUID.randomUUID().toString();
		return this;
	}

	/**
	 * Set {@link #date}
	 *
	 * @param date New {@link #date}
	 * @return The current builder.
	 */
	public BetBuilder withDate(Date date) {
		this.date = date;
		return this;
	}

	/**
	 * Set {@link #user}
	 *
	 * @param user New {@link #user}
	 * @return The current builder.
	 */
	public BetBuilder withUser(User user) {
		this.user = user;
		return this;
	}

	/**
	 * Set {@link #match}
	 *
	 * @param match New {@link #match}
	 * @return The current builder.
	 */
	public BetBuilder withMatch(Match match) {
		this.match = match;
		return this;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 * @return The current builder.
	 */
	public BetBuilder withScore(Score score) {
		this.score = score;
		return this;
	}

	/**
	 * Set {@link #score} with given values.
	 *
	 * @param score1 First score.
	 * @param score2 Second score.
	 * @return The current builder.
	 */
	public BetBuilder withScore(int score1, int score2) {
		return withScore(new ScoreBuilder().withScore(score1, score2).build());
	}

	/**
	 * Build final {@link Bet} instance.
	 *
	 * @return The instance.
	 */
	public Bet build() {
		Bet bet = newInstance(Bet.class);
		writeField(bet, "id", id);
		writeField(bet, "date", date);
		writeField(bet, "match", match);
		writeField(bet, "score", score);
		writeField(bet, "user", user);
		return bet;
	}
}
