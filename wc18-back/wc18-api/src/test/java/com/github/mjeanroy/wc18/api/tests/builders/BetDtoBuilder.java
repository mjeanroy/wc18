/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.tests.builders;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;

/**
 * Builder for {@link BetDto} instances.
 */
public class BetDtoBuilder {

	/**
	 * Set bet user.
	 * @see BetDto#user
	 */
	private UserDto user;

	/**
	 * Set bet match.
	 * @see BetDto#match
	 */
	private MatchDto match;

	/**
	 * Set bet score.
	 * @see BetDto#score
	 */
	private ScoreDto score;

	/**
	 * Set {@link #user}
	 *
	 * @param user New {@link #user}
	 * @return The builder.
	 */
	public BetDtoBuilder withUser(UserDto user) {
		this.user = user;
		return this;
	}

	/**
	 * Set {@link #match}
	 *
	 * @param match New {@link #match}
	 * @return The builder.
	 */
	public BetDtoBuilder withMatch(MatchDto match) {
		this.match = match;
		return this;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score1 New {@link #score} score1
	 * @param score2 New {@link #score} score2
	 * @return The builder.
	 */
	public BetDtoBuilder withScore(int score1, int score2) {
		this.score = new ScoreDtoBuilder().withScore1(score1).withScore2(score2).build();
		return this;
	}

	/**
	 * Create final {@link BetDto} instance.
	 *
	 * @return The instance.
	 */
	public BetDto build() {
		BetDto betDto = new BetDto();
		betDto.setUser(user);
		betDto.setMatch(match);
		betDto.setScore(score);
		return betDto;
	}
}
