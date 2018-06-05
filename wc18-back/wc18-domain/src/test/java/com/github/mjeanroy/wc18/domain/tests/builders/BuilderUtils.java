/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.github.mjeanroy.wc18.domain.tests.builders;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Constructor;

/**
 * Static Reflection Utilities, to use in unit tests only.
 */
final class BuilderUtils {

	// Ensure non instantiation.
	private BuilderUtils() {
	}

	/**
	 * Instantiate class using public/private default constructor.
	 *
	 * @param klass The class.
	 * @param <T> Type of returned value.
	 * @return The new instance.
	 */
	static <T> T newInstance(Class<T> klass) {
		Constructor<T> ctor = null;
		boolean wasAccessible = false;

		try {
			ctor = klass.getDeclaredConstructor();
			wasAccessible = ctor.isAccessible();
			if (!wasAccessible) {
				ctor.setAccessible(true);
			}

			return ctor.newInstance();

		} catch (Exception e) {
			throw new AssertionError(e);
		} finally {
			if (ctor != null && !wasAccessible) {
				ctor.setAccessible(false);
			}
		}
	}

	/**
	 * Write private/public field on given class instance.
	 *
	 * @param target The class instance.
	 * @param fieldName The field name.
	 * @param value The field value.
	 */
	static void writeField(Object target, String fieldName, Object value) {
		try {
			FieldUtils.writeField(target, fieldName, value, true);
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
	}
}
