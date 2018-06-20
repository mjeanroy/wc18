/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
	 *
	 * @see League#id
	 */
	private String id;

	/**
	 * The league name.
	 *
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
