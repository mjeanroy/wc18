/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.tests.builders.MatchBuilder;
import com.github.mjeanroy.wc18.domain.tests.builders.TeamBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchDtoMapperTest extends AbstractDtoMapper {

	private MatchDtoMapper mapper;

	@Before
	public void setUp() {
		ScoreDtoMapper scoreDtoMapper = new ScoreDtoMapper(getMapper());
		TeamDtoMapper teamDtoMapper = new TeamDtoMapper(getMapper());
		mapper = new MatchDtoMapper(getMapper(), teamDtoMapper, scoreDtoMapper);
	}

	@Test
	public void it_should_map_match() {
		Match match = new MatchBuilder()
			.withRandomId()
			.withTeam1(new TeamBuilder().withRandomId().build())
			.withTeam2(new TeamBuilder().withRandomId().build())
			.build();

		MatchDto dto = mapper.from(match);

		assertThat(dto.getId()).isEqualTo(match.getId().toString());
		assertThat(dto.getTeam1().getId()).isEqualTo(match.getTeam1().getId().toString());
		assertThat(dto.getTeam2().getId()).isEqualTo(match.getTeam2().getId().toString());
		assertThat(dto.getScore()).isNull();
	}
}
