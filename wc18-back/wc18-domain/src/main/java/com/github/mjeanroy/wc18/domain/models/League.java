/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;

// @Entity
// @Table(name = "leagues")
public class League extends AbstractEntity {

	/**
	 * The league name.
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	// Required for Hibernate.
	private League() {
	}

	/**
	 * Get {@link #name}
	 *
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}
}
