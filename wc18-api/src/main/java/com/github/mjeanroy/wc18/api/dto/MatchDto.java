/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.dto;

public class MatchDto extends AbstractDto {

	private TeamDto team1;
	private TeamDto team2;
	private ScoreDto score;

	public TeamDto getTeam1() {
		return team1;
	}

	public void setTeam1(TeamDto team1) {
		this.team1 = team1;
	}

	public TeamDto getTeam2() {
		return team2;
	}

	public void setTeam2(TeamDto team2) {
		this.team2 = team2;
	}

	public ScoreDto getScore() {
		return score;
	}

	public void setScore(ScoreDto score) {
		this.score = score;
	}
}
