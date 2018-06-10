/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

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
	 * User leagues.
	 */
	// @ManyToMany
	// @JoinTable(name = "user_leagues")
	// private Set<League> leagues;

	// Default constructor, mandatory for Hibernate.
	private User() {
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
}
