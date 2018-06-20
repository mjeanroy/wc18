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

import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.dto.RankDto;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.api.tests.builders.LeagueDtoBuilder;
import com.github.mjeanroy.wc18.domain.models.League;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class LeagueApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private LeagueApiService leagueApiService;

	@Test
	public void it_should_find_all_league() {
		List<LeagueDto> results = toList(leagueApiService.findAll());
		assertThat(results).hasSize(2);
	}

	@Test
	public void it_should_find_league() {
		String id = "f28d71b3-7509-4e54-a517-b237263bfb02";
		LeagueDto result = leagueApiService.findOne(id);
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(id);
		assertThat(result.getUsers()).isNotEmpty();
	}

	@Test
	public void it_should_find_users_of_league() {
		String id = "f28d71b3-7509-4e54-a517-b237263bfb02";
		List<UserDto> users = toList(leagueApiService.findUsers(id));
		assertThat(users)
				.hasSize(1)
				.extracting(UserDto::getId)
				.contains("e31195bd-1d4e-4915-a3dc-ce901f57903f");
	}

	@Test
	public void it_should_find_league_ranks() {
		String id = "f28d71b3-7509-4e54-a517-b237263bfb02";
		List<RankDto> users = toList(leagueApiService.findRanks(id));
		assertThat(users)
				.hasSize(1)
				.extracting(RankDto::getId, RankDto::getScore, RankDto::getPercentGood, RankDto::getPercentPerfect)
				.contains(
						tuple("e31195bd-1d4e-4915-a3dc-ce901f57903f", 0, 0, 0)
				);
	}

	@Test
	public void it_should_create_league() {
		String name = UUID.randomUUID().toString();
		LeagueDto league = new LeagueDtoBuilder()
				.withName(name)
				.build();

		LeagueDto result = leagueApiService.create(league);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getName()).isEqualTo(name);
		assertThat(result.getUsers()).isEmpty();
	}

	@Test
	public void it_should_add_user_to_league() {
		String id = "f28d71b3-7509-4e54-a517-b237263bfb02";
		String userId = "10cd4d9f-099c-4491-bfdb-a635b2ffc757";

		leagueApiService.addUser(id, userId);

		assertThat(findOne(League.class, id).getUsers()).hasSize(2);
	}
}
