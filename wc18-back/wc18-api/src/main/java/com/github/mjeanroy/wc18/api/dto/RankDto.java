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

public class RankDto {

	/**
	 * The rank identifier, i.e the user identifier.
	 */
	private String id;

	/**
	 * The user.
	 */
	private UserDto user;

	/**
	 * The score.
	 */
	private int score;

	/**
	 * The percentage of good pronostics.
	 */
	private int percentGood;

	/**
	 * The percentage of perfect pronostics.
	 */
	private int percentPerfect;

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
	public int getScore() {
		return score;
	}

	/**
	 * Set {@link #score}
	 *
	 * @param score New {@link #score}
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Get {@link #percentGood}
	 *
	 * @return {@link #percentGood}
	 */
	public int getPercentGood() {
		return percentGood;
	}

	/**
	 * Set {@link #percentGood}
	 *
	 * @param percentGood New {@link #percentGood}
	 */
	public void setPercentGood(int percentGood) {
		this.percentGood = percentGood;
	}

	/**
	 * Get {@link #percentPerfect}
	 *
	 * @return {@link #percentPerfect}
	 */
	public int getPercentPerfect() {
		return percentPerfect;
	}

	/**
	 * Set {@link #percentPerfect}
	 *
	 * @param percentPerfect New {@link #percentPerfect}
	 */
	public void setPercentPerfect(int percentPerfect) {
		this.percentPerfect = percentPerfect;
	}
}
