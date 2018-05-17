/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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

	public enum Result {
		UNAVAILABLE(0),
		LOOSE(0),
		WIN(10),
		PERFECT(15);

		private final int point;

		Result(int point) {
			this.point = point;
		}

		int getPoint() {
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

		int diff1 = score.diff();
		int diff2 = match.getScore().diff();
		if (diff1 == diff2) {
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
		return getResult().point;
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
