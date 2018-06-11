/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
