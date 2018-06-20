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

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Score {

	/**
	 * Score of team 1.
	 */
	@Column(name = "score1")
	private Integer score1;

	/**
	 * Score of team 2.
	 */
	@Column(name = "score2")
	private Integer score2;

	// Default constructor, mandatory for Hibernate.
	private Score() {
	}

	/**
	 * Create score.
	 *
	 * @param score1 First score.
	 * @param score2 Second score.
	 */
	public Score(int score1, int score2) {
		this.score1 = score1;
		this.score2 = score2;
	}

	/**
	 * Get difference between {@link #score1} and {@link #score2}.
	 *
	 * @return The difference.
	 */
	public int diff() {
		return score1 - score2;
	}

	/**
	 * Get {@link #score1}
	 *
	 * @return {@link #score1}
	 */
	public Integer getScore1() {
		return score1;
	}

	/**
	 * Get {@link #score2}
	 *
	 * @return {@link #score2}
	 */
	public Integer getScore2() {
		return score2;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof Score) {
			Score s = (Score) o;
			return Objects.equals(score1, s.score1) && Objects.equals(score2, s.score2);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score1, score2);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
			.add("score1", score1)
			.add("score2", score2)
			.toString();
	}
}

