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

package com.github.mjeanroy.wc18.domain.services;

import com.github.mjeanroy.wc18.domain.dao.StageDao;
import com.github.mjeanroy.wc18.domain.exceptions.StageNotFoundException;
import com.github.mjeanroy.wc18.domain.models.Stage;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractServiceTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StageServiceTest extends AbstractServiceTest {

	private StageService stageService;

	@Override
	protected void createService() {
		StageDao stageDao = new StageDao();
		stageService = new StageService(stageDao);
	}

	@Test
	void it_should_find_all() {
		List<Stage> stages = toList(stageService.findAll());
		assertThat(stages).containsExactly(Stage.values());
	}

	@Test
	void it_should_find_one() {
		for (Stage stage : Stage.values()) {
			Stage result = stageService.findOneOrFail(stage.name());
			assertThat(result).isEqualTo(stage);
		}
	}

	@Test
	void it_should_fail_to_find_one_with_unknown_id() {
		assertThatThrownBy(() -> stageService.findOneOrFail("FAKE_STAGE"))
			.isExactlyInstanceOf(StageNotFoundException.class)
			.hasMessage("Cannot find stage 'FAKE_STAGE'");
	}
}
