/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.TeamDto;
import com.github.mjeanroy.wc18.domain.models.Team;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamDtoMapperTest extends AbstractDtoMapper {

	private TeamDtoMapper mapper;

	@Before
	public void setUp() {
		mapper = new TeamDtoMapper(getMapper());
	}

	@Test
	public void it_should_map_team() {
		Team team = new TeamBuilder()
			.withRandomId()
			.withIsoCode("FR")
			.withName("France")
			.build();

		TeamDto dto = mapper.from(team);

		assertThat(dto.getId()).isEqualTo(team.getId().toString());
		assertThat(dto.getIsoCode()).isEqualTo(team.getIsoCode());
		assertThat(dto.getName()).isEqualTo(team.getName());
	}
}
