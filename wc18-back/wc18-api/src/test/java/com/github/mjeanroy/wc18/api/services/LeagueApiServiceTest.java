/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
