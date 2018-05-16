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

	@Column(name = "date", nullable = false)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "team1_id", nullable = false)
	private Team team1;

	@ManyToOne
	@JoinColumn(name = "team2_id", nullable = false)
	private Team team2;

	@Embedded
	private Score score;

	public Date getDate() {
		return date;
	}

	public Team getTeam1() {
		return team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public Score getScore() {
		return score;
	}
}
