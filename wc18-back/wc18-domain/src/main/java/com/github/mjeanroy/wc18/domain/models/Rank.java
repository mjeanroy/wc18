/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
