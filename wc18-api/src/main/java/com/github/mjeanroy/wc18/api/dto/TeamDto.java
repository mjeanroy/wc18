/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

/**
 * The team DTO.
 */
public class TeamDto extends AbstractDto {

	/**
	 * Team ISO code.
	 */
	private String isoCode;

	/**
	 * Team Name.
	 */
	private String name;

	/**
	 * Get {@link #isoCode}
	 *
	 * @return {@link #isoCode}
	 */
	public String getIsoCode() {
		return isoCode;
	}

	/**
	 * Set {@link #isoCode}
	 *
	 * @param isoCode New {@link #isoCode}
	 */
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
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
	 *
	 * @param name New {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}
}
