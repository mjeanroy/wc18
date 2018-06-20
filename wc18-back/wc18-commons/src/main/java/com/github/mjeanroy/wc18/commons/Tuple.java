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

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * A tuple object: a pair of a left side value and right side value.
 *
 * @param <T> Type of left side value.
 * @param <U> Type of right side value.
 */
public final class Tuple<T, U> {

	/**
	 * Create the tuple object.
	 *
	 * @param left The left side of tuple.
	 * @param right The right side of tuple.
	 * @param <T> Type of left side object.
	 * @param <U> Type of right side object.
	 * @return The new tuple object.
	 */
	public static <T, U> Tuple<T, U> tuple(T left, U right) {
		return new Tuple<>(left, right);
	}

	/**
	 * The left side of the tuple.
	 */
	private final T left;

	/**
	 * The right side of the tuple.
	 */
	private final U right;

	/**
	 * Create the tuple object.
	 *
	 * @param left Left side of tuple.
	 * @param right Right side of tuple.
	 */
	private Tuple(T left, U right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Get {@link #left}
	 *
	 * @return {@link #left}
	 */
	public T getLeft() {
		return left;
	}

	/**
	 * Get {@link #right}
	 *
	 * @return {@link #right}
	 */
	public U getRight() {
		return right;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o instanceof Tuple) {
			Tuple t = (Tuple) o;
			return Objects.equals(left, t.left) && Objects.equals(right, t.right);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
			.add("left", left)
			.add("right", right)
			.toString();
	}
}
