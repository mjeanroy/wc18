/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.Team;

import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;

/**
 * Builder for {@link Team} instances.
 */
public class TeamBuilder {

	/**
	 * The team id.
	 * @see Team#id
	 */
	private String id;

	/**
	 * The team ISO code.
	 * @see Team#isoCode
	 */
	private String isoCode;

	/**
	 * The team name.
	 * @see Team#name
	 */
	private String name;

	/**
	 * Set {@link #id} with a random value.
	 *
	 * @return The current builder.
	 */
	public TeamBuilder withRandomId() {
		this.id = UUID.randomUUID().toString();
		return this;
	}

	/**
	 * Set {@link #id}.
	 *
	 * @param id New {@link #id}
	 * @return The current builder.
	 */
	public TeamBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #isoCode}.
	 *
	 * @param isoCode New {@link #isoCode}
	 * @return The current builder.
	 */
	public TeamBuilder withIsoCode(String isoCode) {
		this.isoCode = isoCode;
		return this;
	}

	/**
	 * Set {@link #name}.
	 *
	 * @param name New {@link #name}
	 * @return The current builder.
	 */
	public TeamBuilder withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Build the final {@link Team} instance.
	 *
	 * @return The instance.
	 */
	public Team build() {
		Team team = newInstance(Team.class);
		writeField(team, "id", id);
		writeField(team, "isoCode", isoCode);
		writeField(team, "name", name);
		return team;
	}
}
