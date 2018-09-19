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

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.tests.builders.BetDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.MatchDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.PrincipalBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.UserDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.junit.AbstractApiServiceTest;
import com.github.mjeanroy.wc18.domain.exceptions.MatchLockedException;
import com.github.mjeanroy.wc18.security.models.Principal;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
	@Ignore
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

	@Test
	public void it_should_not_save_bet_for_locked_match() {
		Principal principal = new PrincipalBuilder()
			.withLogin("john")
			.build();

		BetDto bet = new BetDtoBuilder()
			.withMatch(new MatchDtoBuilder().withId("def3918f-d885-45d5-93ae-f8782c17d7a7").build())
			.withUser(new UserDtoBuilder().withId("10cd4d9f-099c-4491-bfdb-a635b2ffc757").build())
			.withScore(1, 0)
			.build();

		assertThatThrownBy(() -> betApiService.save(principal, bet))
			.isExactlyInstanceOf(MatchLockedException.class)
			.hasMessage("Match 'def3918f-d885-45d5-93ae-f8782c17d7a7' is locked for bet");
	}
}
