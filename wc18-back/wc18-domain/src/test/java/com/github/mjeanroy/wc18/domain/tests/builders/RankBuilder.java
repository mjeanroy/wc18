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
