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
	 * The match id.
	 * @see Team#id
	 */
	private UUID id;

	/**
	 * Set {@link #id} with a random value.
	 *
	 * @return The current builder.
	 */
	public TeamBuilder withRandomId() {
		this.id = UUID.randomUUID();
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
		return team;
	}
}
