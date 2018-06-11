/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Static Collection Utilities.
 */
public final class MoreCollections {

	// Ensure non instantiation.
	private MoreCollections() {
	}

	/**
	 * Create new hashmap from given tuple entries.
	 *
	 * @param first First entry.
	 * @param <T> Type of key in the map.
	 * @param <U> Type of values in the map.
	 * @return The resulting map.
	 */
	public static <T, U> Map<T, U> newHashMap(Tuple<T, U> first) {
		Map<T, U> map = new HashMap<>();
		map.put(first.getLeft(), first.getRight());
		return map;
	}

	/**
	 * Create new hashmap from given tuple entries.
	 *
	 * @param entry First entry.
	 * @param second Second entry.
	 * @param <T> Type of key in the map.
	 * @param <U> Type of values in the map.
	 * @return The resulting map.
	 */
	public static <T, U> Map<T, U> newHashMap(Tuple<T, U> entry, Tuple<T, U> second) {
		Map<T, U> map = new HashMap<>();
		map.put(entry.getLeft(), entry.getRight());
		map.put(second.getLeft(), second.getRight());
		return map;
	}
}
