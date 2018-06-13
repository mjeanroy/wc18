/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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

	/**
	 * User leagues.
	 */
	@ManyToMany
	@JoinTable(name = "user_leagues",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "league_id"))
	private Set<League> leagues;

	// Default constructor, mandatory for Hibernate.
	private User() {
		super();
		this.leagues = new HashSet<>();
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

	/**
	 * Get {@link #leagues}
	 *
	 * @return {@link #leagues}
	 */
	public Set<League> getLeagues() {
		return leagues;
	}
}
