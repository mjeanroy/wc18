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

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.TeamDto;

import java.util.UUID;

/**
 * Builder for {@link TeamDto} instances.
 */
public class TeamDtoBuilder {

	/**
	 * The team id.
	 *
	 * @see TeamDto#id
	 */
	private String id;

	/**
	 * The team iso code.
	 *
	 * @see TeamDto#isoCode
	 */
	private String isoCode;

	/**
	 * The team name.
	 *
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
