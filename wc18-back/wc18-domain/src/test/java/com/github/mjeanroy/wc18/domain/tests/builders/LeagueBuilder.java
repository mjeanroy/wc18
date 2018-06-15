/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.User;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;
import static java.util.Collections.addAll;

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
	 * The league users.
	 */
	private final Set<User> users;

	/**
	 * Create the builder.
	 */
	public LeagueBuilder() {
		this.users = new LinkedHashSet<>();
	}

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
	 * Add new users to the league.
	 *
	 * @param users The users list.
	 * @return The current builder.
	 */
	public LeagueBuilder withUsers(User... users) {
		addAll(this.users, users);
		return this;
	}

	/**
	 * Build the final {@link League} instance.
	 *
	 * @return The instance.
	 */
	public League build() {
		League league = newInstance(League.class);
		writeField(league, "id", id);
		writeField(league, "name", name);
		writeField(league, "users", new LinkedHashSet<>(this.users));
		return league;
	}
}
