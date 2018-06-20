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

package com.github.mjeanroy.wc18.domain.models;

import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {

	@Test
	public void it_should_check_if_match_is_played() {
		Match match = new MatchBuilder()
			.withRandomId()
			.withScore(1, 0)
			.build();

		assertThat(match.isPlayed()).isTrue();
	}

	@Test
	public void it_should_check_if_match_is_not_played() {
		Match match = new MatchBuilder()
			.withRandomId()
			.build();

		assertThat(match.isPlayed()).isFalse();
	}

	@Test
	public void it_should_check_if_match_is_locked() {
		Match match = new MatchBuilder()
			.withRandomId()
			.withDate(Date.from(LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC)))
			.build();

		assertThat(match.isLocked()).isTrue();
	}

	@Test
	public void it_should_check_if_match_is_not_locked() {
		Match match = new MatchBuilder()
			.withRandomId()
			.withDate(Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC)))
			.build();

		assertThat(match.isLocked()).isFalse();
	}
}
