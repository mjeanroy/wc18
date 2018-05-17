/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Score {

	@Column(name = "score1")
	private Integer score1;

	@Column(name = "score2")
	private Integer score2;

	private Score() {
	}

	public Score(int score1, int score2) {
		this.score1 = score1;
		this.score2 = score2;
	}

	public Integer getScore1() {
		return score1;
	}

	public Integer getScore2() {
		return score2;
	}
}

