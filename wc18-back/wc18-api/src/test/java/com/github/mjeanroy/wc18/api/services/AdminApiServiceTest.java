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

import com.github.mjeanroy.wc18.api.dto.CommitDto;
import com.github.mjeanroy.wc18.api.tests.junit.AbstractApiServiceTest;
import org.assertj.core.api.Condition;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static com.github.mjeanroy.wc18.domain.tests.commons.IterableTestUtils.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class AdminApiServiceTest extends AbstractApiServiceTest {

	@Inject
	private AdminApiService adminApiService;

	@Test
	public void it_should_read_changelog() {
		List<CommitDto> changelog = toList(adminApiService.readChangeLog());

		assertThat(changelog)
			.isNotEmpty()
			.are(new Condition<CommitDto>() {
				@Override
				public boolean matches(CommitDto value) {
					return value.getAuthorName() != null && !value.getAuthorName().isEmpty();
				}
			})
			.are(new Condition<CommitDto>() {
				@Override
				public boolean matches(CommitDto value) {
					return value.getMessage() != null && !value.getMessage().isEmpty();
				}
			})
			.are(new Condition<CommitDto>() {
				@Override
				public boolean matches(CommitDto value) {
					return value.getId() != null && !value.getId().isEmpty();
				}
			});
	}
}
