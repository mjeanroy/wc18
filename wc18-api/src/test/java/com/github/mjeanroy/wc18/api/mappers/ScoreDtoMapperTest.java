/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.tests.builders.ScoreBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreDtoMapperTest extends AbstractDtoMapper {

	private ScoreDtoMapper scoreDtoMapper;

	@Before
	public void setUp() {
		scoreDtoMapper = new ScoreDtoMapper(getMapper());
	}

	@Test
	public void it_should_map_score_dto_to_score() {
		Score score = new ScoreBuilder()
			.withScore(1, 0)
			.build();

		ScoreDto dto = scoreDtoMapper.from(score);

		assertThat(dto).isNotNull();
		assertThat(dto.getScore1()).isEqualTo(score.getScore1());
		assertThat(dto.getScore2()).isEqualTo(score.getScore2());
	}
}
