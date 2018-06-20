/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
