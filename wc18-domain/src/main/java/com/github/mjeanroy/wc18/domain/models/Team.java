/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team extends AbstractEntity {

	/**
	 * The team flag code, ISO value on two characters.
	 */
	@Column(name = "iso_code", nullable = false, length = 2)
	private String isoCode;

	/**
	 * The team name.
	 */
	@Column(name = "name", nullable = false)
	private String name;

	// The empty constructor is mandatory for hibernate.
	private Team() {
	}

	/**
	 * Get {@link #isoCode}
	 *
	 * @return {@link #isoCode}
	 */
	public String getIsoCode() {
		return isoCode;
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
