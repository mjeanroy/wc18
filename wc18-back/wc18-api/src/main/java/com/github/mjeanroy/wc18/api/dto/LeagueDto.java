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

package com.github.mjeanroy.wc18.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LeagueDto extends AbstractDto {

	/**
	 * The league identifier.
	 */
	private String id;

	/**
	 * The league login.
	 */
	@NotBlank
	@Size(min = 3, max = 50)
	private String name;

	/**
	 * User in the league.
	 */
	private Iterable<UserDto> users;

	/**
	 * Get {@link #id}
	 *
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get {@link #name}
	 *
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set {@link #name}
	 *
	 * @param name New {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get {@link #users}
	 *
	 * @return {@link #users}
	 */
	public Iterable<UserDto> getUsers() {
		return users;
	}

	/**
	 * Set {@link #users}
	 *
	 * @param users New {@link #users}
	 */
	public void setUsers(Iterable<UserDto> users) {
		this.users = users;
	}
}
