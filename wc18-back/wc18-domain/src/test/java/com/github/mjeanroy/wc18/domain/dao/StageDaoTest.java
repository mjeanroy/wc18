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

package com.github.mjeanroy.wc18.domain.dao;

import com.github.mjeanroy.wc18.domain.models.Stage;
import com.github.mjeanroy.wc18.domain.tests.junit.AbstractRepositoryTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class StageDaoTest extends AbstractRepositoryTest {

	@Inject
	private StageDao stageDao;

	@Test
	public void it_should_find_all() {
		List<Stage> stages = toList(stageDao.findAll());
		assertThat(stages)
			.hasSameSizeAs(Stage.values())
			.containsExactly(Stage.values());
	}

	@Test
	public void it_should_find_one() {
		for (Stage stage : Stage.values()) {
			Optional<Stage> result = stageDao.findOne(stage.name());
			assertThat(result).isPresent().hasValue(stage);
		}
	}
}
