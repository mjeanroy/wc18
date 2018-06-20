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

package com.github.mjeanroy.wc18.domain.tests.builders;

import com.github.mjeanroy.wc18.domain.tests.commons.ReflectionTestUtils;

import java.lang.reflect.Constructor;

/**
 * Static Reflection Utilities, to use in unit tests only.
 */
public final class BuilderUtils {

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
		ReflectionTestUtils.writeField(target, fieldName, value);
	}
}
