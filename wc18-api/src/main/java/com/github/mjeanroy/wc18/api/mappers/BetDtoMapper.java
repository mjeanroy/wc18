/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.BetDto;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.domain.models.Bet;
import com.github.mjeanroy.wc18.domain.models.Match;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link Match} to {@link MatchDto}.
 */
@Component
public class BetDtoMapper extends AbstractLazyObjectMapper<Bet, BetDto> {

	private final MatchDtoMapper matchDtoMapper;
	private final ScoreDtoMapper scoreDtoMapper;

	@Inject
	public BetDtoMapper(Mapper mapper, MatchDtoMapper matchDtoMapper, ScoreDtoMapper scoreDtoMapper) {
		super(mapper);
		this.matchDtoMapper = matchDtoMapper;
		this.scoreDtoMapper = scoreDtoMapper;
	}

	@Override
	public BetDto convert(Bet source) {
		BetDto dto = new BetDto();
		dto.setId(source.getId());
		dto.setDate(source.getDate());
		dto.setMatch(matchDtoMapper.from(source.getMatch()));
		dto.setScore(scoreDtoMapper.from(source.getScore()));
		return dto;
	}
}
