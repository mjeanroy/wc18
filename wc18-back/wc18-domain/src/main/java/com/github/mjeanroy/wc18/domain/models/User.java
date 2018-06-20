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

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	public enum Role {
		USER, ADMIN
	}

	/**
	 * The user login.
	 */
	@Column(name = "login", nullable = false, unique = true)
	private String login;

	/**
	 * The user password.
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * The user role.
	 */
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	// Default constructor, mandatory for Hibernate.
	private User() {
		super();
	}

	/**
	 * Create new user.
	 *
	 * @param login User login.
	 * @param password User password.
	 */
	public User(String login, String password) {
		this();
		this.login = login;
		this.password = password;
		this.role = Role.USER;
	}

	/**
	 * Update user password.
	 *
	 * @param password User password.
	 */
	public void updatePassword(String password) {
		this.password = password;
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
	 * Get {@link #password}
	 *
	 * @return {@link #password}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Get {@link #role}
	 *
	 * @return {@link #role}
	 */
	public Role getRole() {
		return role;
	}
}
