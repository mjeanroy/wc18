/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.api.dto.RankDto;
import com.github.mjeanroy.wc18.domain.models.League;
import com.github.mjeanroy.wc18.domain.models.Rank;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link League} to {@link LeagueDto}.
 */
@Component
public class RankDtoMapper extends AbstractLazyObjectMapper<Rank, RankDto> {

	private final UserDtoMapper userDtoMapper;

	@Inject
	public RankDtoMapper(Mapper mapper, UserDtoMapper userDtoMapper) {
		super(mapper);
		this.userDtoMapper = userDtoMapper;
	}

	@Override
	public RankDto convert(Rank source) {
		RankDto dto = new RankDto();
		dto.setId(source.getId());
		dto.setUser(userDtoMapper.from(source.getUser()));
		dto.setScore(source.getScore());
		dto.setPercentGood(source.getPercentGood());
		dto.setPercentPerfect(source.getPercentPerfect());
		return dto;
	}
}
