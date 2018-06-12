/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.domain.models.Match;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link Match} to {@link MatchDto}.
 */
@Component
public class MatchDtoMapper extends AbstractLazyObjectMapper<Match, MatchDto> {

	private final TeamDtoMapper teamDtoMapper;
	private final ScoreDtoMapper scoreDtoMapper;

	@Inject
	public MatchDtoMapper(Mapper mapper, TeamDtoMapper teamDtoMapper, ScoreDtoMapper scoreDtoMapper) {
		super(mapper);
		this.teamDtoMapper = teamDtoMapper;
		this.scoreDtoMapper = scoreDtoMapper;
	}

	@Override
	public MatchDto convert(Match source) {
		MatchDto dto = new MatchDto();
		dto.setId(source.getId());
		dto.setDate(source.getDate());
		dto.setStage(source.getStage());
		dto.setTeam1(teamDtoMapper.from(source.getTeam1()));
		dto.setTeam2(teamDtoMapper.from(source.getTeam2()));
		dto.setScore(scoreDtoMapper.from(source.getScore()));
		return dto;
	}
}
