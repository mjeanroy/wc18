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

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.api.dto.TeamDto;
import com.github.mjeanroy.wc18.api.tests.builders.MatchDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.ScoreDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.builders.TeamDtoBuilder;
import com.github.mjeanroy.wc18.api.tests.junit.AbstractApiServiceTest;
import com.github.mjeanroy.wc18.domain.models.Stage;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private MatchApiService matchApiService;

	@Test
	public void it_should_find_all_matches() {
		Iterable<MatchDto> matches = matchApiService.findAll(null);
		assertThat(matches)
			.isNotNull()
			.isNotEmpty()
			.extracting(MatchDto::getId)
			.doesNotContainNull();
	}

	@Test
	public void it_should_create_match() {
		TeamDto team1 = new TeamDtoBuilder()
			.withId("5820fadd-ae19-48d5-b4e5-811b08f58b87")
			.withName("France")
			.build();

		TeamDto team2 = new TeamDtoBuilder()
			.withId("e9c4e714-5b4b-4e2d-a896-9f043c295869")
			.withName("Brésil")
			.build();

		MatchDto dto = new MatchDtoBuilder()
			.withDate(new Date())
			.withStage(Stage.FINAL)
			.withTeam1(team1)
			.withTeam2(team2)
			.build();

		MatchDto result = matchApiService.create(dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getDate()).isEqualTo(dto.getDate());
		assertThat(result.getStage()).isNotNull();
		assertThat(result.getStage().getId()).isEqualTo(Stage.FINAL.name());
		assertThat(result.getStage().getLabel()).isEqualTo(Stage.FINAL.getLabel());
		assertThat(result.getTeam1().getId()).isEqualTo(team1.getId());
		assertThat(result.getTeam2().getId()).isEqualTo(team2.getId());
	}

	@Test
	public void it_should_update_match() {
		TeamDto team1 = new TeamDtoBuilder()
			.withId("5820fadd-ae19-48d5-b4e5-811b08f58b87")
			.withName("France")
			.build();

		TeamDto team2 = new TeamDtoBuilder()
			.withId("e2b8bae1-06ab-44d2-8595-7af3e2441718")
			.withName("Australie")
			.build();

		int score1 = 3;
		int score2 = 0;

		MatchDto dto = new MatchDtoBuilder()
			.withId("4ff9c731-7eba-47bb-91fa-a0c04b2e394e")
			.withDate(new Date())
			.withStage(Stage.FINAL)
			.withTeam1(team1)
			.withTeam2(team2)
			.withScore(score1, score2)
			.build();

		MatchDto result = matchApiService.update(dto.getId(), dto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getDate()).isEqualTo(dto.getDate());
		assertThat(result.getStage()).isNotNull();
		assertThat(result.getStage().getId()).isEqualTo(Stage.FINAL.name());
		assertThat(result.getStage().getLabel()).isEqualTo(Stage.FINAL.getLabel());
		assertThat(result.getScore().getScore1()).isEqualTo(score1);
		assertThat(result.getScore().getScore2()).isEqualTo(score2);
	}

	@Test
	public void it_should_update_match_score() {
		TeamDto team1 = new TeamDtoBuilder()
			.withId("5820fadd-ae19-48d5-b4e5-811b08f58b87")
			.withName("France")
			.build();

		TeamDto team2 = new TeamDtoBuilder()
			.withId("e2b8bae1-06ab-44d2-8595-7af3e2441718")
			.withName("Australie")
			.build();

		int score1 = 3;
		int score2 = 0;
		ScoreDto scoreDto = new ScoreDtoBuilder()
			.withScore1(score1)
			.withScore2(score2)
			.build();

		MatchDto dto = new MatchDtoBuilder()
			.withId("4ff9c731-7eba-47bb-91fa-a0c04b2e394e")
			.withDate(new Date())
			.withStage(Stage.FINAL)
			.withTeam1(team1)
			.withTeam2(team2)
			.build();

		MatchDto result = matchApiService.updateScore(dto.getId(), scoreDto);

		assertThat(result).isNotNull();
		assertThat(result.getId()).isNotNull();
		assertThat(result.getScore().getScore1()).isEqualTo(score1);
		assertThat(result.getScore().getScore2()).isEqualTo(score2);
	}

	@Test
	public void it_should_remove_match() {
		String id = "7c2ae500-e5ae-4574-ad5e-1b4ef770c8d8";
		matchApiService.remove(id);
	}
}
