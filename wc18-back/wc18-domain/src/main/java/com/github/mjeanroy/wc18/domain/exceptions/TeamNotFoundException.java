/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.exceptions;

import com.github.mjeanroy.wc18.domain.exceptions.AbstractException;

public class TeamNotFoundException extends AbstractException {

	/**
	 * The team identifier.
	 */
	private final String id;

	/**
	 * Create the exception with given not found identifier.
	 *
	 * @param id The missing identifier.
	 */
	public TeamNotFoundException(String id) {
		super(String.format("Cannot find team '%s'", id));
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
