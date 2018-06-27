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
	private final StageDtoMapper stageDtoMapper;

	@Inject
	public MatchDtoMapper(Mapper mapper, TeamDtoMapper teamDtoMapper, ScoreDtoMapper scoreDtoMapper, StageDtoMapper stageDtoMapper) {
		super(mapper);
		this.teamDtoMapper = teamDtoMapper;
		this.scoreDtoMapper = scoreDtoMapper;
		this.stageDtoMapper = stageDtoMapper;
	}

	@Override
	public MatchDto convert(Match source) {
		MatchDto dto = new MatchDto();
		dto.setId(source.getId());
		dto.setDate(source.getDate());
		dto.setStage(stageDtoMapper.from(source.getStage()));
		dto.setTeam1(teamDtoMapper.from(source.getTeam1()));
		dto.setTeam2(teamDtoMapper.from(source.getTeam2()));
		dto.setScore(scoreDtoMapper.from(source.getScore()));
		return dto;
	}
}
