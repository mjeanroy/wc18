/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.ScoreDto;

/**
 * Builder for {@link ScoreDto} instances.
 */
public class ScoreDtoBuilder {

	/**
	 * The team id.
	 * @see ScoreDto#score1
	 */
	private int score1;

	/**
	 * The team iso code.
	 * @see ScoreDto#score2
	 */
	private int score2;

	/**
	 * Set {@link #score1}
	 *
	 * @param score1 New {@link #score1}
	 * @return The builder.
	 */
	public ScoreDtoBuilder withScore1(int score1) {
		this.score1 = score1;
		return this;
	}

	/**
	 * Set {@link #score2}
	 *
	 * @param score2 New {@link #score2}
	 * @return The builder.
	 */
	public ScoreDtoBuilder withScore2(int score2) {
		this.score2 = score2;
		return this;
	}

	/**
	 * Build final {@link ScoreDto} instance.
	 *
	 * @return The final instance.
	 */
	public ScoreDto build() {
		ScoreDto dto = new ScoreDto();
		dto.setScore1(score1);
		dto.setScore2(score2);
		return dto;
	}
}
