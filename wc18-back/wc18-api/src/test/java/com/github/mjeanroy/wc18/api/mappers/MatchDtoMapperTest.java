/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Match.Stage;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;

import javax.inject.Inject;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchDtoMapperTest extends AbstractDtoMapperTest<Match, MatchDto, MatchDtoMapper> {

	@Inject
	private MatchDtoMapper matchDtoMapper;

	@Override
	MatchDtoMapper getMapper() {
		return matchDtoMapper;
	}

	@Override
	Match createInput() {
		return new MatchBuilder()
			.withRandomId()
				.withDate(new Date())
				.withStage(Stage.GROUP)
				.withTeam1(new TeamBuilder().withRandomId().build())
				.withTeam2(new TeamBuilder().withRandomId().build())
				.build();
	}

	@Override
	void verifyOutput(Match input, MatchDto output) {
		assertThat(output.getId()).isEqualTo(input.getId());
		assertThat(output.getDate()).isEqualTo(input.getDate());
		assertThat(output.getStage()).isEqualTo(input.getStage());
		assertThat(output.getTeam1().getId()).isEqualTo(input.getTeam1().getId());
		assertThat(output.getTeam2().getId()).isEqualTo(input.getTeam2().getId());
		assertThat(output.getScore()).isNull();
	}
}
