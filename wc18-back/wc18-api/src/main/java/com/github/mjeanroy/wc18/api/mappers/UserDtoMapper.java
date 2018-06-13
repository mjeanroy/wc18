/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.api.mappers;

import com.github.mjeanroy.spring.mappers.Mapper;
import com.github.mjeanroy.spring.mappers.objects.AbstractLazyObjectMapper;
import com.github.mjeanroy.wc18.api.dto.UserDto;
import com.github.mjeanroy.wc18.domain.models.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper translating {@link User} to {@link UserDto}.
 */
@Component
public class UserDtoMapper extends AbstractLazyObjectMapper<User, UserDto> {

	private final LeagueDtoMapper leagueDtoMapper;

	@Inject
	public UserDtoMapper(Mapper mapper, LeagueDtoMapper leagueDtoMapper) {
		super(mapper);
		this.leagueDtoMapper = leagueDtoMapper;
	}

	@Override
	public UserDto convert(User source) {
		UserDto dto = new UserDto();
		dto.setId(source.getId());
		dto.setLogin(source.getLogin());
		dto.setRole(source.getRole());
		dto.setLeagues(leagueDtoMapper.from(source.getLeagues()));
		return dto;
	}
}
