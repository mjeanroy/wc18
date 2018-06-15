/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.tests.builders.ScoreBuilder;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreDtoMapperTest extends AbstractDtoMapperTest<Score, ScoreDto, ScoreDtoMapper> {

	@Inject
	private ScoreDtoMapper scoreDtoMapper;

	@Override
	ScoreDtoMapper getMapper() {
		return scoreDtoMapper;
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
