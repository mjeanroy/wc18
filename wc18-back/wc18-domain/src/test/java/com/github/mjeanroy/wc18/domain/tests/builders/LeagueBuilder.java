/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.League;

import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;

/**
 * Builder for {@link League} instances.
 */
public class LeagueBuilder {

	/**
	 * The league id.
	 * @see League#id
	 */
	private String id;

	/**
	 * The league name.
	 * @see League#name
	 */
	private String name;

	/**
	 * Set {@link #id} with a random value.
	 *
	 * @return The current builder.
	 */
	public LeagueBuilder withRandomId() {
		this.id = UUID.randomUUID().toString();
		return this;
	}

	/**
	 * Set {@link #id}.
	 *
	 * @param name New {@link #name}
	 * @return The current builder.
	 */
	public LeagueBuilder withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Build the final {@link League} instance.
	 *
	 * @return The instance.
	 */
	public League build() {
		League user = newInstance(League.class);
		writeField(user, "id", id);
		writeField(user, "name", name);
		return user;
	}
}
