/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.domain.models.Score;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link Score} to {@link ScoreDto}.
 */
@Component
public class ScoreDtoMapper extends AbstractLazyObjectMapper<Score, ScoreDto> {

	@Inject
	public ScoreDtoMapper(Mapper mapper) {
		super(mapper);
	}

	@Override
	public ScoreDto convert(Score source) {
		ScoreDto dto = new ScoreDto();
		dto.setScore1(source.getScore1());
		dto.setScore2(source.getScore2());
		return dto;
	}
}
