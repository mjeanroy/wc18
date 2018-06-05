/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.models.Team;

import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;

/**
 * Builder for {@link Match} instances.
 */
public class ScoreBuilder {

	/**
	 * The first score.
	 * @see Score#score1
	 */
	private Integer score1;

	/**
	 * The second score.
	 * @see Score#score2
	 */
	private Integer score2;

	/**
	 * Set {@link #score1}
	 *
	 * @param score1 New {@link #score1}
	 * @return The current builder.
	 */
	public ScoreBuilder withScore1(int score1) {
		this.score1 = score1;
		return this;
	}

	/**
	 * Set {@link #score2}
	 *
	 * @param score2 New {@link #score2}
	 * @return The current builder.
	 */
	public ScoreBuilder withScore2(int score2) {
		this.score2 = score2;
		return this;
	}

	/**
	 * Set {@link #score1} and {@link #score2}
	 *
	 * @param score1 New {@link #score1}
	 * @param score2 New {@link #score2}
	 * @return The current builder.
	 */
	public ScoreBuilder withScore(int score1, int score2) {
		return withScore1(score1).withScore2(score2);
	}

	/**
	 * Build the final {@link Score} instance.
	 *
	 * @return The instance.
	 */
	public Score build() {
		Score match = newInstance(Score.class);
		writeField(match, "score1", score1);
		writeField(match, "score2", score2);
		return match;
	}
}
