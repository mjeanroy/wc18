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
