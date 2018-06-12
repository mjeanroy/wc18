/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.models.Team;

import java.util.Date;
import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.newInstance;
import static com.github.mjeanroy.wc18.domain.tests.builders.BuilderUtils.writeField;

/**
 * Builder for {@link Match} instances.
 */
public class MatchBuilder {

	/**
	 * The match id.
	 * @see Match#id
	 */
	private String id;

	/**
	 * The match stage.
	 * @see Match#stage
	 */
	private Match.Stage stage;

	/**
	 * The match date.
	 * @see Match#date
	 */
	private Date date;

	/**
	 * The match first team.
	 * @see Match#team1
	 */
	private Team team1;

	/**
	 * The match second team.
	 * @see Match#team2
	 */
	private Team team2;

	/**
	 * The match score.
	 * @see Match#score
	 */
	private Score score;

	/**
	 * Set {@link #id} with a random value.
	 *
	 * @return The current builder.
	 */
	public MatchBuilder withRandomId() {
		this.id = UUID.randomUUID().toString();
		return this;
	}

	/**
	 * Set {@link #id}.
	 *
	 * @param id New {@link #id}
	 * @return The current builder.
	 */
	public MatchBuilder withId(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Set {@link #stage}.
	 *
	 * @param stage New {@link #stage}
	 * @return The current builder.
	 */
	public MatchBuilder withStage(Match.Stage stage) {
		this.stage = stage;
		return this;
	}

	/**
	 * Set {@link #date}.
	 *
	 * @param date New {@link #date}
	 * @return The current builder.
	 */
	public MatchBuilder withDate(Date date) {
		this.date = date;
		return this;
	}

	/**
	 * Set {@link #team1}.
	 *
	 * @param team1 New {@link #team1}
	 * @return The current builder.
	 */
	public MatchBuilder withTeam1(Team team1) {
		this.team1 = team1;
		return this;
	}

	/**
	 * Set {@link #team2}.
	 *
	 * @param team2 New {@link #team2}
	 * @return The current builder.
	 */
	public MatchBuilder withTeam2(Team team2) {
		this.team2 = team2;
		return this;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 * @return The current builder.
	 */
	public MatchBuilder withScore(Score score) {
		this.score = score;
		return this;
	}

	/**
	 * Set {@link #score} with given values.
	 *
	 * @param score1 First score.
	 * @param score2 Second score.
	 * @return The current builder.
	 */
	public MatchBuilder withScore(int score1, int score2) {
		return withScore(new ScoreBuilder().withScore(score1, score2).build());
	}

	/**
	 * Build the final match instance.
	 *
	 * @return The match instance.
	 */
	public Match build() {
		Match match = newInstance(Match.class);
		writeField(match, "id", id);
		writeField(match, "date", date);
		writeField(match, "stage", stage);
		writeField(match, "team1", team1);
		writeField(match, "team2", team2);
		writeField(match, "score", score);
		return match;
	}
}
