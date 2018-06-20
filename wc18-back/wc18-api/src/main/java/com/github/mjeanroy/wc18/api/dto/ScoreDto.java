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
