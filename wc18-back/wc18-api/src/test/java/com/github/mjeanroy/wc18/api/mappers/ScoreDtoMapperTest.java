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

import com.github.mjeanroy.wc18.api.dto.ScoreDto;
import com.github.mjeanroy.wc18.domain.models.Score;
import com.github.mjeanroy.wc18.domain.tests.builders.ScoreBuilder;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreDtoMapperTest extends AbstractDtoMapperTest<Score, ScoreDto, ScoreDtoMapper> {

	@Inject
	private ScoreDtoMapper scoreDtoMapper;

	@Override
	ScoreDtoMapper getMapper() {
		return scoreDtoMapper;
	}

	@Override
	Score createInput() {
		return new ScoreBuilder()
			.withScore(1, 0)
			.build();
	}

	@Override
	void verifyOutput(Score input, ScoreDto output) {
		assertThat(output).isNotNull();
		assertThat(output.getScore1()).isEqualTo(input.getScore1());
		assertThat(output.getScore2()).isEqualTo(input.getScore2());
	}
}
