/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Static Iterable Utilities, to use in unit test only.
 */
public final class IterableTestUtils {

	// Ensure non instantiation.
	private IterableTestUtils() {
	}

	/**
	 * Create list from given iterables structure.
	 *
	 * @param iterables The iterables elements.
	 * @param <T> Type of element in collection.
	 * @return The output list.
	 */
	public static <T> List<T> toList(Iterable<T> iterables) {
		if (iterables instanceof List) {
			return (List<T>) iterables;
		}

		List<T> outputs = new ArrayList<>(tryToGetSizeOfIterables(iterables));
		for (T iterable : iterables) {
			outputs.add(iterable);
		}

		return outputs;
	}

	private static <T> int tryToGetSizeOfIterables(Iterable<T> iterables) {
		if (iterables instanceof Collection) {
			return ((Collection) iterables).size();
		}

		return 10;
	}
}
