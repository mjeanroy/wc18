/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.wc18.api.dto.TeamDto;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamDtoMapperTest extends AbstractDtoMapper<Team, TeamDto, TeamDtoMapper> {

	@Override
	TeamDtoMapper create(Mapper mapper) {
		return new TeamDtoMapper(mapper);
	}

	@Override
	Team createInput() {
		return new TeamBuilder()
			.withRandomId()
			.withIsoCode("FR")
			.withName("France")
			.build();
	}

	@Override
	void verifyOutput(Team input, TeamDto output) {
		assertThat(output.getId()).isEqualTo(input.getId());
		assertThat(output.getIsoCode()).isEqualTo(input.getIsoCode());
		assertThat(output.getName()).isEqualTo(input.getName());
	}
}
