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
