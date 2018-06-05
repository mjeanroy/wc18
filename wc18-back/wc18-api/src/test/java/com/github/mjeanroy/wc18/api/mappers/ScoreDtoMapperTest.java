/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.tests.builders.ScoreBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreDtoMapperTest extends AbstractDtoMapper<Score, ScoreDto, ScoreDtoMapper> {

	@Override
	ScoreDtoMapper create(Mapper mapper) {
		return new ScoreDtoMapper(mapper);
	}

	@Override
	Score createInput() {
		return new ScoreBuilder()
			.withScore(1, 0)
			.build();
	}

	@Override
	void verifyOutput(Score input, ScoreDto output) {
		assertThat(output).isNotNull();
		assertThat(output.getScore1()).isEqualTo(input.getScore1());
		assertThat(output.getScore2()).isEqualTo(input.getScore2());
	}
}
