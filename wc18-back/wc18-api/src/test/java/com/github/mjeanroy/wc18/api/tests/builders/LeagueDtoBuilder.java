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
