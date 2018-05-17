/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

import java.util.Date;

public class BetDto extends AbstractDto {

	/**
	 * The match.
	 */
	private MatchDto match;

	/**
	 * The user.
	 */
	private UserDto user;

	/**
	 * The score.
	 */
	private ScoreDto score;

	/**
	 * The date.
	 */
	private Date date;

	/**
	 * Get {@link #match}
	 *
	 * @return {@link #match}
	 */
	public MatchDto getMatch() {
		return match;
	}

	/**
	 * Set {@link #match}
	 *
	 * @param match New {@link #match}
	 */
	public void setMatch(MatchDto match) {
		this.match = match;
	}

	/**
	 * Get {@link #user}
	 *
	 * @return {@link #user}
	 */
	public UserDto getUser() {
		return user;
	}

	/**
	 * Set {@link #user}
	 *
	 * @param user New {@link #user}
	 */
	public void setUser(UserDto user) {
		this.user = user;
	}

	/**
	 * Get {@link #score}
	 *
	 * @return {@link #score}
	 */
	public ScoreDto getScore() {
		return score;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 */
	public void setScore(ScoreDto score) {
		this.score = score;
	}

	/**
	 * Get {@link #date}
	 *
	 * @return {@link #date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set {@link #date}
	 *
	 * @param date New {@link #date}
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
