/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.LeagueDto;
import com.github.mjeanroy.wc18.domain.models.League;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link League} to {@link LeagueDto}.
 */
@Component
public class LeagueDtoMapper extends AbstractLazyObjectMapper<League, LeagueDto> {

	private final UserDtoMapper userDtoMapper;

	@Inject
	public LeagueDtoMapper(Mapper mapper, UserDtoMapper userDtoMapper) {
		super(mapper);
		this.userDtoMapper = userDtoMapper;
	}

	@Override
	public LeagueDto convert(League source) {
		LeagueDto dto = new LeagueDto();
		dto.setId(source.getId());
		dto.setName(source.getName());
		dto.setUsers(userDtoMapper.from(source.getUsers()));
		return dto;
	}
}
