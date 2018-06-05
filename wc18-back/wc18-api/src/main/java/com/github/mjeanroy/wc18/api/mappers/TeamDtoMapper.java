/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.MatchDto;
import com.github.mjeanroy.wc18.api.dto.TeamDto;
import com.github.mjeanroy.wc18.domain.models.Match;
import com.github.mjeanroy.wc18.domain.models.Team;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link Team} to {@link TeamDto}.
 */
@Component
public class TeamDtoMapper extends AbstractLazyObjectMapper<Team, TeamDto> {

	@Inject
	public TeamDtoMapper(Mapper mapper) {
		super(mapper);
	}

	@Override
	public TeamDto convert(Team source) {
		TeamDto dto = new TeamDto();
		dto.setId(source.getId());
		dto.setIsoCode(source.getIsoCode());
		dto.setName(source.getName());
		return dto;
	}
}
