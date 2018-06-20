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
	 *
	 * @see Team#id
	 */
	private String id;

	/**
	 * The team ISO code.
	 *
	 * @see Team#isoCode
	 */
	private String isoCode;

	/**
	 * The team name.
	 *
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
