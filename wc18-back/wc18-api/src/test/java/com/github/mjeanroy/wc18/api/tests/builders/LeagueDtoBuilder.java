/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.LeagueDto;

/**
 * Builder for {@link LeagueDto} instances.
 */
public class LeagueDtoBuilder {

	/**
	 * The league id.
	 * @see LeagueDto#id
	 */
	private String id;

	/**
	 * The league name.
	 * @see LeagueDto#name
	 */
	private String name;

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 * @return The builder.
	 */
	public LeagueDtoBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #name}
	 *
	 * @param name New {@link #name}
	 * @return The builder.
	 */
	public LeagueDtoBuilder withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Create the final {@link LeagueDto} instance.
	 *
	 * @return The instance.
	 */
	public LeagueDto build() {
		LeagueDto dto = new LeagueDto();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}
}
