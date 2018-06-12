/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LeagueDto extends AbstractDto {

	/**
	 * The user identifier.
	 */
	private String id;

	/**
	 * The user login.
	 */
	@NotBlank
	@Size(min = 3, max = 50)
	private String name;

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
	 * Get {@link #name}
	 *
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set {@link #name}
	 * @param name New {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}
}
