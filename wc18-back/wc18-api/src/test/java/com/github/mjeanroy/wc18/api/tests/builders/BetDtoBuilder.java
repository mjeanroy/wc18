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
	 *
	 * @see BetDto#user
	 */
	private UserDto user;

	/**
	 * Set bet match.
	 *
	 * @see BetDto#match
	 */
	private MatchDto match;

	/**
	 * Set bet score.
	 *
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
