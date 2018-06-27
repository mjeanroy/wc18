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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "matches")
public class Match extends AbstractEntity {

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
	@JoinColumn(name = "team1_id", nullable = false, updatable = false)
	private Team team1;

	/**
	 * The second team.
	 */
	@ManyToOne
	@JoinColumn(name = "team2_id", nullable = false, updatable = false)
	private Team team2;

	/**
	 * The score, may be {@code null} until match has been played.
	 */
	@Embedded
	private Score score;

	// Empty constructor, mandatory for hibernate.
	private Match() {
		super();
	}

	/**
	 * Create new match.
	 *
	 * @param date The match date.
	 * @param stage The match stage.
	 * @param team1 The first team.
	 * @param team2 The second team.
	 */
	public Match(Date date, Stage stage, Team team1, Team team2) {
		this();

		setDate(date);
		setStage(stage);
		setTeam1(team1);
		setTeam2(team2);
	}

	/**
	 * Update match date.
	 *
	 * @param date New match date.
	 */
	public void updateDate(Date date) {
		if (!Objects.equals(date, this.date)) {
			setDate(date);
		}
	}

	/**
	 * Update match stage.
	 *
	 * @param stage New match stage.
	 */
	public void updateStage(Stage stage) {
		if (!Objects.equals(stage, this.stage)) {
			setStage(stage);
		}
	}

	/**
	 * Update match score.
	 *
	 * @param score1 First score.
	 * @param score2 Second score.
	 */
	public void updateScore(Integer score1, Integer score2) {
		Score score = score1 == null || score2 == null ? null : new Score(score1, score2);
		if (!Objects.equals(score, this.score)) {
			setScore(score);
		}
	}

	/**
	 * The coefficient to apply per bet.
	 *
	 * @return The coefficient.
	 */
	public int getCoeff() {
		return stage.getCoeff();
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

	/**
	 * Set {@link #team1}
	 *
	 * @param team1 New {@link #team1}
	 */
	private void setTeam1(Team team1) {
		this.team1 = team1;
	}

	/**
	 * Set {@link #date}
	 *
	 * @param date New {@link #date}
	 */
	private void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Set {@link #stage}
	 *
	 * @param stage New {@link #stage}
	 */
	private void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Set {@link #team2}
	 *
	 * @param team2 New {@link #team2}
	 */
	private void setTeam2(Team team2) {
		this.team2 = team2;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 */
	private void setScore(Score score) {
		this.score = score;
	}
}
