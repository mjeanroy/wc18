/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.Rank;
import com.github.mjeanroy.wc18.domain.models.User;

/**
 * Builder for {@link Rank} instance.
 */
public class RankBuilder {

	/**
	 * The ranked user.
	 * @see Rank#user
	 */
	private User user;

	/**
	 * The ranked score.
	 * @see Rank#score
	 */
	private int score;

	/**
	 * The ranked percent of good pronostics.
	 * @see Rank#percentGood
	 */
	private int percentGood;

	/**
	 * The ranked percent of perfect pronostics.
	 * @see Rank#percentPerfect
	 */
	private int percentPerfect;

	/**
	 * Create the builder with a default user.
	 */
	public RankBuilder() {
		this.user = new UserBuilder().withRandomId().build();
	}

	/**
	 * Set {@link #user}
	 *
	 * @param user New {@link #user}
	 * @return The builder.
	 */
	public RankBuilder withUser(User user) {
		this.user = user;
		return this;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 * @return The builder.
	 */
	public RankBuilder withScore(int score) {
		this.score = score;
		return this;
	}

	/**
	 * Set {@link #percentGood}
	 *
	 * @param percentGood New {@link #percentGood}
	 * @return The builder.
	 */
	public RankBuilder withPercentGood(int percentGood) {
		this.percentGood = percentGood;
		return this;
	}

	/**
	 * Set {@link #percentPerfect}
	 *
	 * @param percentPerfect New {@link #percentPerfect}
	 * @return The builder.
	 */
	public RankBuilder withPercentPerfect(int percentPerfect) {
		this.percentPerfect = percentPerfect;
		return this;
	}

	/**
	 * Create the final {@link Rank} instance.
	 *
	 * @return The final instance.
	 */
	public Rank build() {
		return new Rank(user, score, percentGood, percentPerfect);
	}
}
