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
