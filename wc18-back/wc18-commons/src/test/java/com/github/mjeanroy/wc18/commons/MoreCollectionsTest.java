/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.commons;

import org.junit.Test;

import java.util.Map;

import static com.github.mjeanroy.wc18.commons.MoreCollections.newHashMap;
import static com.github.mjeanroy.wc18.commons.Tuple.tuple;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class MoreCollectionsTest {

	@Test
	public void it_should_create_hash_map_with_single_entry() {
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
	public void it_should_create_hash_map_with_two_entries() {
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
