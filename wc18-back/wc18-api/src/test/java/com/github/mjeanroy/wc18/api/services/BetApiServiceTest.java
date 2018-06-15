/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.tests.builders.BetDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.MatchDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.PrincipalBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.UserDtoBuilder;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class BetApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private BetApiService betApiService;

	@Test
	public void it_should_find_all_bets_of_user() {
		Principal principal = new PrincipalBuilder()
				.withLogin("mickael")
				.build();

		List<BetDto> bets = toList(betApiService.findAll(principal, null));

		assertThat(bets).isNotEmpty();
	}

	@Test
	public void it_should_save_new_bet() {
		Principal principal = new PrincipalBuilder()
				.withLogin("john")
				.build();

		BetDto bet = new BetDtoBuilder()
				.withMatch(new MatchDtoBuilder().withId("7c2ae500-e5ae-4574-ad5e-1b4ef770c8d8").build())
				.withUser(new UserDtoBuilder().withId("10cd4d9f-099c-4491-bfdb-a635b2ffc757").build())
				.withScore(1, 0)
				.build();

		BetDto result = betApiService.save(principal, bet);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getUser()).isNotNull();
		assertThat(result.getMatch()).isNotNull();
		assertThat(result.getScore().getScore1()).isEqualTo(1);
		assertThat(result.getScore().getScore2()).isEqualTo(0);
	}
}
