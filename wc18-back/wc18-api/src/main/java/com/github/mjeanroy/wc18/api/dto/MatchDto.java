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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class MatchDto extends AbstractDto {

	/**
	 * The match id.
	 */
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
	private StageDto stage;

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
	public StageDto getStage() {
		return stage;
	}

	/**
	 * Set {@link #stage}.
	 *
	 * @param stage New {@link #stage}
	 */
	public void setStage(StageDto stage) {
		this.stage = stage;
	}
}
