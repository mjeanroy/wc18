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

import com.github.mjeanroy.wc18.domain.models.User.Role;

public class UserDto extends AbstractDto {

	/**
	 * The user identifier.
	 */
	private String id;

	/**
	 * The user login.
	 */
	private String login;

	/**
	 * The user role.
	 */
	private Role role;

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
	 * Get {@link #login}
	 *
	 * @return {@link #login}
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set {@link #login}
	 * @param login New {@link #login}
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get {@link #role}
	 *
	 * @return {@link #role}
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set {@link #role}
	 *
	 * @param role New {@link #role}
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
