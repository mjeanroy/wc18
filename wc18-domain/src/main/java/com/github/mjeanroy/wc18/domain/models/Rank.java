/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

public class Rank {

	/**
	 * The user.
	 */
	private final User user;

	/**
	 * Number of points won by user.
	 */
	private final int points;

	// Default constructor.
	public Rank(User user, int points) {
		this.user = user;
		this.points = points;
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
	 * Get {@link #points}
	 *
	 * @return {@link #points}
	 */
	public int getPoints() {
		return points;
	}
}
