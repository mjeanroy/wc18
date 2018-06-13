/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.commons;

/**
 * Static String Utilities.
 */
public final class MoreStrings {

	// Ensure non instantiation.
	private MoreStrings() {
	}

	/**
	 * Check if a string is {@code null} or empty.
	 *
	 * @param value The input value.
	 * @return {@code true} if {@code value} is {@code null} or empty, {@code false} otherwise.
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	/**
	 * Check if a string is not {@code null} and not empty.
	 *
	 * @param value The input value.
	 * @return {@code true} if {@code value} is not {@code null} and not empty, {@code false} otherwise.
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
}
