/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

public class RankDto {

	/**
	 * The rank identifier, i.e the user identifier.
	 */
	private String id;

	/**
	 * The user.
	 */
	private UserDto user;

	/**
	 * The score.
	 */
	private int score;

	/**
	 * The percentage of good pronostics.
	 */
	private int percentGood;

	/**
	 * The percentage of perfect pronostics.
	 */
	private int percentPerfect;

	/**
	 * Get {@link #id}
	 *
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 */
	public void setId(String id) {
		this.id = id;
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
	public int getScore() {
		return score;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 */
	public void setScore(int score) {
		this.score = score;
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
	 * Set {@link #percentGood}
	 *
	 * @param percentGood New {@link #percentGood}
	 */
	public void setPercentGood(int percentGood) {
		this.percentGood = percentGood;
	}

	/**
	 * Get {@link #percentPerfect}
	 *
	 * @return {@link #percentPerfect}
	 */
	public int getPercentPerfect() {
		return percentPerfect;
	}

	/**
	 * Set {@link #percentPerfect}
	 *
	 * @param percentPerfect New {@link #percentPerfect}
	 */
	public void setPercentPerfect(int percentPerfect) {
		this.percentPerfect = percentPerfect;
	}
}
