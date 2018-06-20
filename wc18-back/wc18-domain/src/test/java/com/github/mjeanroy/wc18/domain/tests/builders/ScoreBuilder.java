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

import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Score;

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
