/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

import com.github.mjeanroy.wc18.domain.models.Match.Stage;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class MatchDto extends AbstractDto {

	/**
	 * The match id.
	 */
	@NotBlank
	private String id;

	/**
	 * The match date.
	 */
	@NotNull
	private Date date;

	/**
	 * The match stage.
	 */
	@NotNull
	private Stage stage;

	/**
	 * The first team.
	 */
	@NotNull
	@Valid
	private TeamDto team1;

	/**
	 * The second team.
	 */
	@NotNull
	@Valid
	private TeamDto team2;

	/**
	 * The score, may be {@code null} until match has been played.
	 */
	@Valid
	private ScoreDto score;

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
	 * Get {@link #date}
	 *
	 * @return {@link #date}
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set {@link #date}
	 *
	 * @param date New {@link #date}
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Get {@link #team1}
	 *
	 * @return {@link #team1}
	 */
	public TeamDto getTeam1() {
		return team1;
	}

	/**
	 * Set {@link #team1}
	 *
	 * @param team1 New {@link #team1}
	 */
	public void setTeam1(TeamDto team1) {
		this.team1 = team1;
	}

	/**
	 * Get {@link #team2}
	 *
	 * @return {@link #team2}
	 */
	public TeamDto getTeam2() {
		return team2;
	}

	/**
	 * Set {@link #team2}
	 *
	 * @param team2 New {@link #team2}
	 */
	public void setTeam2(TeamDto team2) {
		this.team2 = team2;
	}

	/**
	 * Get {@link #score}
	 *
	 * @return {@link #score}
	 */
	public ScoreDto getScore() {
		return score;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 */
	public void setScore(ScoreDto score) {
		this.score = score;
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
	 * Set {@link #stage}.
	 *
	 * @param stage New {@link #stage}
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
