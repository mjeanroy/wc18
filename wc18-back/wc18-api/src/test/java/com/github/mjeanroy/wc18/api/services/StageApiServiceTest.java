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

package com.github.mjeanroy.wc18.api.services;

import com.github.mjeanroy.wc18.api.dto.StageDto;
import com.github.mjeanroy.wc18.api.tests.junit.AbstractApiServiceTest;
import com.github.mjeanroy.wc18.domain.models.Stage;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class StageApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private StageApiService stageApiService;

	@Test
	public void it_should_get_all_stages() {
		Iterable<StageDto> stages = stageApiService.findAll();
		assertThat(stages)
			.hasSameSizeAs(Stage.values())
			.extracting(StageDto::getId, StageDto::getLabel)
			.contains(
				tuple(Stage.GROUP.name(), Stage.GROUP.getLabel()),
				tuple(Stage.ROUND_16.name(), Stage.ROUND_16.getLabel()),
				tuple(Stage.QUARTER_FINAL.name(), Stage.QUARTER_FINAL.getLabel()),
				tuple(Stage.SEMI_FINAL.name(), Stage.SEMI_FINAL.getLabel()),
				tuple(Stage.THIRD_PLACE_FINAL.name(), Stage.THIRD_PLACE_FINAL.getLabel()),
				tuple(Stage.FINAL.name(), Stage.FINAL.getLabel())
			);
	}
}
