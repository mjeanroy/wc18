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

package com.github.mjeanroy.wc18.commons;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.github.mjeanroy.wc18.commons.MoreCollections.newHashMap;
import static com.github.mjeanroy.wc18.commons.Tuple.tuple;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class MoreCollectionsTest {

	@Test
	void it_should_create_hash_map_with_single_entry() {
		String key = "foo";
		String value = "bar";
		Map<String, String> map = newHashMap(tuple(key, value));

		assertThat(map)
			.isNotNull()
			.hasSize(1)
			.containsOnly(
				entry(key, value)
			);
	}

	@Test
	void it_should_create_hash_map_with_two_entries() {
		String key1 = "foo1";
		String value1 = "bar1";

		String key2 = "foo2";
		String value2 = "bar2";

		Map<String, String> map = newHashMap(
			tuple(key1, value1),
			tuple(key2, value2)
		);

		assertThat(map)
			.isNotNull()
			.hasSize(2)
			.containsOnly(
				entry(key1, value1),
				entry(key2, value2)
			);
	}
}
