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

import com.github.mjeanroy.wc18.domain.models.Bet;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BetDto extends AbstractDto {

	/**
	 * The bet id.
	 */
	private String id;

	/**
	 * The date.
	 */
	private Date date;

	/**
	 * The match.
	 */
	@NotNull
	private MatchDto match;

	/**
	 * The score.
	 */
	@NotNull
	@Valid
	private ScoreDto score;

	/**
	 * The user.
	 */
	private UserDto user;

	/**
	 * The bet result.
	 */
	private Bet.Result result;

	/**
	 * The bet point.
	 */
	private int point;

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
	 * Get {@link #match}
	 *
	 * @return {@link #match}
	 */
	public MatchDto getMatch() {
		return match;
	}

	/**
	 * Set {@link #match}
	 *
	 * @param match New {@link #match}
	 */
	public void setMatch(MatchDto match) {
		this.match = match;
	}

	/**
	 * Get {@link #user}
	 *
	 * @return {@link #user}
	 */
	public UserDto getUser() {
		return user;
	}

	/**
	 * Set {@link #user}
	 *
	 * @param user New {@link #user}
	 */
	public void setUser(UserDto user) {
		this.user = user;
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
	 * Get {@link #result}
	 *
	 * @return {@link #result}
	 */
	public Bet.Result getResult() {
		return result;
	}

	/**
	 * Set {@link #result}
	 *
	 * @param result New {@link #result}
	 */
	public void setResult(Bet.Result result) {
		this.result = result;
	}

	/**
	 * Get {@link #point}
	 *
	 * @return {@link #point}
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * Set {@link #point}
	 *
	 * @param point New {@link #point}
	 */
	public void setPoint(int point) {
		this.point = point;
	}
}
