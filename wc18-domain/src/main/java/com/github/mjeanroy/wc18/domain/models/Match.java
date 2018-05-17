/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "matchs")
public class Match extends AbstractEntity {

	/**
	 * The match date, in UTC.
	 */
	@Column(name = "date", nullable = false)
	private Date date;

	/**
	 * The first team.
	 */
	@ManyToOne
	@JoinColumn(name = "team1_id", nullable = false)
	private Team team1;

	/**
	 * The second team.
	 */
	@ManyToOne
	@JoinColumn(name = "team2_id", nullable = false)
	private Team team2;

	/**
	 * The score, may be {@code null} until match has been played.
	 */
	@Embedded
	private Score score;

	// Empty constructor, mandatory for hibernate.
	private Match() {
	}

	/**
	 * Get {@link #date}
	 *
	 * @return {@link #date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Get {@link #team1}
	 *
	 * @return {@link #team1}
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * Get {@link #team2}
	 *
	 * @return {@link #team2}
	 */
	public Team getTeam2() {
		return team2;
	}

	/**
	 * Get {@link #score}
	 *
	 * @return {@link #score}
	 */
	public Score getScore() {
		return score;
	}
}
