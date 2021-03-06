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

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bets")
public class Bet extends AbstractEntity {

	/**
	 * The bet result.
	 */
	public enum Result {
		/**
		 * The result is not available and can't be computed: it happens when match result
		 * is not available yet.
		 */
		UNAVAILABLE(0),

		/**
		 * Happens when final result (i.e final winner) is different than the real result.
		 */
		LOOSE(0),

		/**
		 * Happens when final result is OK (same winner, or equality.
		 */
		WIN(10),

		/**
		 * Happens when bet score is exactly the same as the real result.
		 */
		PERFECT(15);

		/**
		 * Number of point for given result.
		 */
		private final int point;

		Result(int point) {
			this.point = point;
		}

		/**
		 * Get {@link #point}
		 *
		 * @return {@link #point}
		 */
		public int getPoint() {
			return point;
		}
	}

	/**
	 * The user that have created this bet.
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;

	/**
	 * The match.
	 */
	@ManyToOne
	@JoinColumn(name = "match_id", nullable = false, updatable = false)
	private Match match;

	/**
	 * The bet date.
	 */
	@Column(name = "date", nullable = false)
	private Date date;

	/**
	 * The score, will never be {@code null}.
	 */
	@Embedded
	private Score score;

	// Default constructor, mandatory for Hibernate.
	private Bet() {
		this.date = new Date();
	}

	/**
	 * Create the bet.
	 *
	 * @param user The user.
	 * @param match The match.
	 * @param score The score.
	 */
	public Bet(User user, Match match, Score score) {
		this();
		this.user = user;
		this.match = match;
		this.score = score;
	}

	/**
	 * Get the bet result.
	 *
	 * @return Bet result.
	 */
	public Result getResult() {
		if (!match.isPlayed()) {
			return Result.UNAVAILABLE;
		}

		if (score.equals(match.getScore())) {
			return Result.PERFECT;
		}

		int sign1 = Integer.signum(match.getScore().diff());
		int sign2 = Integer.signum(score.diff());
		if (sign1 == sign2) {
			return Result.WIN;
		}

		return Result.LOOSE;
	}

	/**
	 * Get points won by given {@link #user}.
	 *
	 * @return Points.
	 */
	public int getPoint() {
		return getResult().point * match.getCoeff();
	}

	/**
	 * Update match score.
	 *
	 * @param score The new score.
	 */
	public void updateScore(Score score) {
		this.date = new Date();
		this.score = score;
	}

	/**
	 * Get coefficient to apply.
	 *
	 * @return The coefficient.
	 */
	public int getCoeff() {
		return match.getCoeff();
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
	 * Get {@link #match}
	 *
	 * @return {@link #match}
	 */
	public Match getMatch() {
		return match;
	}

	/**
	 * Get {@link #score}
	 *
	 * @return {@link #score}
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * Get {@link #date}
	 *
	 * @return {@link #date}
	 */
	public Date getDate() {
		return date;
	}
}
