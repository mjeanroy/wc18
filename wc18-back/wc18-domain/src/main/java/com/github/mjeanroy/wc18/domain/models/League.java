/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "leagues")
public class League extends AbstractEntity {

	/**
	 * The league name.
	 */
	@Column(name = "name", nullable = false, unique = true, updatable = false)
	private String name;

	/**
	 * User leagues.
	 */
	@ManyToMany
	@JoinTable(name = "user_leagues",
			joinColumns = @JoinColumn(name = "league_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	// Required for Hibernate.
	private League() {
		super();
		this.users = new HashSet<>();
	}

	/**
	 * Create new league.
	 *
	 * @param name League name.
	 */
	public League(String name) {
		this();
		this.name = name;
	}

	/**
	 * Add user to given league.
	 *
	 * @param user User.
	 */
	public void addUser(User user) {
		this.users.add(user);
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
	 * Get {@link #users}
	 *
	 * @return {@link #users}
	 */
	public Set<User> getUsers() {
		return users;
	}
}
