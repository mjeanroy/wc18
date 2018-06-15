/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.exceptions;

public class MatchUnlockedException extends AbstractException {

	/**
	 * The match identifier.
	 */
	private final String id;

	/**
	 * Create the exception with given locked match identifier.
	 *
	 * @param id Match identifier.
	 */
	public MatchUnlockedException(String id) {
		super(String.format("Match '%s' must be locked to get bet", id));
		this.id = id;
	}

	/**
	 * Get {@link #id}
	 *
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}
}
