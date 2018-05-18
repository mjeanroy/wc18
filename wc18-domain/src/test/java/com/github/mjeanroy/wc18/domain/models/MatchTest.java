/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
