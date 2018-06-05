/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match extends AbstractEntity {

	/**
	 * The match type.
	 */
	public enum Stage {
		GROUP,
		ROUND_16,
		QUARTER_FINAL,
		SEMI_FINAL,
		THIRD_PLACE_FINAL,
		FINAL
	}

	/**
	 * The match date, in UTC.
	 */
	@Column(name = "date", nullable = false)
	private Date date;

	/**
	 * The match stage.
	 */
	@Column(name = "stage", nullable = false)
	@Enumerated(EnumType.STRING)
	private Stage stage;

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
	 * Check if match has been played and score is available.
	 *
	 * @return {@code true} if match has been played, {@code false} otherwise.
	 */
	public boolean isPlayed() {
		return score != null;
	}

	/**
	 * Check if match is locked, i.e if it is eligible to a bet.
	 *
	 * @return {@code true} if match is locked, {@code false} otherwise.
	 */
	public boolean isLocked() {
		return isPlayed() || new Date().after(date);
	}

	/**
	 * Get {@link #stage}
	 *
	 * @return {@link #stage}
	 */
	public Stage getStage() {
		return stage;
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
