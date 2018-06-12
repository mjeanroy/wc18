/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.commons;

import org.apache.commons.lang3.reflect.FieldUtils;

public final class ReflectionTestUtils {

	// Ensure non instantiation.
	private ReflectionTestUtils() {
	}

	/**
	 * Write private/public field on given class instance.
	 *
	 * @param target The class instance.
	 * @param fieldName The field name.
	 * @param value The field value.
	 */
	public static void writeField(Object target, String fieldName, Object value) {
		try {
			FieldUtils.writeField(target, fieldName, value, true);
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
	}
}
