/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ScoreDto {

	/**
	 * The score of team 1.
	 */
	@NotNull
	@Min(0)
	@Max(15)
	private Integer score1;

	/**
	 * The score of team 2.
	 */
	@NotNull
	@Min(0)
	@Max(15)
	private Integer score2;

	/**
	 * Get {@link #score1}
	 *
	 * @return {@link #score1}
	 */
	public Integer getScore1() {
		return score1;
	}

	/**
	 * Set {@link #score1}
	 *
	 * @param score1 New {@link #score1}
	 */
	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	/**
	 * Get {@link #score2}
	 *
	 * @return {@link #score2}
	 */
	public Integer getScore2() {
		return score2;
	}

	/**
	 * Set {@link #score2}
	 *
	 * @param score2 New {@link #score2}
	 */
	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
}
