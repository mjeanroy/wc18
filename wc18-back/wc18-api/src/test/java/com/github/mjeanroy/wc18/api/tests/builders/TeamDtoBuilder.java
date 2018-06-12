/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.TeamDto;

import java.util.UUID;

/**
 * Builder for {@link TeamDto} instances.
 */
public class TeamDtoBuilder {

	/**
	 * The team id.
	 * @see TeamDto#id
	 */
	private String id;

	/**
	 * The team iso code.
	 * @see TeamDto#isoCode
	 */
	private String isoCode;

	/**
	 * The team name.
	 * @see TeamDto#name
	 */
	private String name;

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 * @return The builder.
	 */
	public TeamDtoBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #id}
	 *
	 * @return The builder.
	 */
	public TeamDtoBuilder withRandomId() {
		return withId(UUID.randomUUID().toString());
	}

	/**
	 * Set {@link #name}
	 *
	 * @param name New {@link #name}
	 * @return The builder.
	 */
	public TeamDtoBuilder withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Set {@link #isoCode}
	 *
	 * @param isoCode New {@link #isoCode}
	 * @return The builder.
	 */
	public TeamDtoBuilder withIsoCode(String isoCode) {
		this.isoCode = isoCode;
		return this;
	}

	/**
	 * Build final {@link TeamDto} instance.
	 *
	 * @return The final instance.
	 */
	public TeamDto build() {
		TeamDto dto = new TeamDto();
		dto.setId(id);
		dto.setName(name);
		dto.setIsoCode(isoCode);
		return dto;
	}
}
