/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.exceptions;

import com.github.mjeanroy.wc18.domain.exceptions.AbstractException;

public class MatchNotFoundException extends AbstractException {

	/**
	 * The match identifier.
	 */
	private final String id;

	/**
	 * Create the exception with given not found identifier.
	 *
	 * @param id The missing identifier.
	 */
	public MatchNotFoundException(String id) {
		super(String.format("Cannot find match '%s'", id));
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
