/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
