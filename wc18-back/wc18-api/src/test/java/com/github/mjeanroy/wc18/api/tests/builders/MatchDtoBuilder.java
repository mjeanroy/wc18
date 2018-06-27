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

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.api.dto.StageDto;
import com.github.mjeanroy.wc18.api.dto.TeamDto;
import com.github.mjeanroy.wc18.domain.models.Stage;

import java.util.Date;
import java.util.UUID;

/**
 * Builder for {@link MatchDto} instances.
 */
public class MatchDtoBuilder {

	/**
	 * The match id.
	 *
	 * @see MatchDto#id
	 */
	private String id;

	/**
	 * The match date.
	 *
	 * @see MatchDto#date
	 */
	private Date date;

	/**
	 * The match stage.
	 *
	 * @see MatchDto#stage
	 */
	private StageDto stage;

	/**
	 * The match first team.
	 *
	 * @see MatchDto#team1
	 */
	private TeamDto team1;

	/**
	 * The match second team.
	 *
	 * @see MatchDto#team2
	 */
	private TeamDto team2;

	/**
	 * The match score.
	 *
	 * @see MatchDto#score
	 */
	private ScoreDto score;

	/**
	 * Set {@link #id}
	 *
	 * @param id New {@link #id}
	 * @return The builder.
	 */
	public MatchDtoBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #id} with a random value.
	 *
	 * @return The builder.
	 */
	public MatchDtoBuilder withRandomId() {
		return withId(UUID.randomUUID().toString());
	}

	/**
	 * Set {@link #date}
	 *
	 * @param date New {@link #date}
	 * @return The builder.
	 */
	public MatchDtoBuilder withDate(Date date) {
		this.date = date;
		return this;
	}

	/**
	 * Set {@link #stage}
	 *
	 * @param stage New {@link #stage}
	 * @return The builder.
	 */
	public MatchDtoBuilder withStage(Stage stage) {
		this.stage = new StageDtoBuilder().with(stage).build();
		return this;
	}

	/**
	 * Set {@link #team1}
	 *
	 * @param team1 New {@link #team1}
	 * @return The builder.
	 */
	public MatchDtoBuilder withTeam1(TeamDto team1) {
		this.team1 = team1;
		return this;
	}

	/**
	 * Set {@link #team2}
	 *
	 * @param team2 New {@link #team2}
	 * @return The builder.
	 */
	public MatchDtoBuilder withTeam2(TeamDto team2) {
		this.team2 = team2;
		return this;
	}

	/**
	 * Set {@link #score} with given values.
	 *
	 * @param score1 First score.
	 * @param score2 Second score.
	 * @return The builder.
	 */
	public MatchDtoBuilder withScore(int score1, int score2) {
		this.score = new ScoreDtoBuilder().withScore1(score1).withScore2(score2).build();
		return this;
	}

	/**
	 * Build final {@link MatchDto} instance.
	 *
	 * @return The final instance.
	 */
	public MatchDto build() {
		MatchDto dto = new MatchDto();
		dto.setId(id);
		dto.setDate(date);
		dto.setStage(stage);
		dto.setTeam1(team1);
		dto.setTeam2(team2);
		dto.setScore(score);
		return dto;
	}
}
