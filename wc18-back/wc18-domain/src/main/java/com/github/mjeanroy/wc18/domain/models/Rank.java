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

package com.github.mjeanroy.wc18.domain.models;

public class Rank {

	/**
	 * The identifier.
	 */
	private final String id;

	/**
	 * The user.
	 */
	private final User user;

	/**
	 * Number of points won by user.
	 */
	private final int score;

	/**
	 * The percentage of good pronostics.
	 */
	private final int percentGood;

	/**
	 * The percentage of perfect pronostics.
	 */
	private final int percentPerfect;

	// Default constructor.
	public Rank(User user, int score, int percentGood, int percentPerfect) {
		this.id = user.getId();
		this.user = user;
		this.score = score;
		this.percentGood = percentGood;
		this.percentPerfect = percentPerfect;
	}

	/**
	 * Get {@link #id}
	 *
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get {@link #user}
	 *
	 * @return {@link #user}
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Get {@link #score}
	 *
	 * @return {@link #score}
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Get {@link #percentGood}
	 *
	 * @return {@link #percentGood}
	 */
	public int getPercentGood() {
		return percentGood;
	}

	/**
	 * Get {@link #percentPerfect}
	 *
	 * @return {@link #percentPerfect}
	 */
	public int getPercentPerfect() {
		return percentPerfect;
	}
}
