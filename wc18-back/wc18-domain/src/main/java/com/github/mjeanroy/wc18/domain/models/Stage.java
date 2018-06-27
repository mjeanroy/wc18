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

package com.github.mjeanroy.wc18.domain.models;

/**
 * The match type.
 */
public enum Stage {

	GROUP("Groupe", 1),
	ROUND_16("Huitième de finale", 2),
	QUARTER_FINAL("Quarts de finale", 3),
	SEMI_FINAL("Demi finale", 5),
	THIRD_PLACE_FINAL("Match troisième place", 8),
	FINAL("Finale", 10);

	/**
	 * The stage label.
	 */
	private final String label;

	/**
	 * The coefficient to compute point per bet.
	 */
	private final int coeff;

	Stage(String label, int coeff) {
		this.label = label;
		this.coeff = coeff;
	}

	/**
	 * Get {@link #label}
	 *
	 * @return {@link #label}
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Get {@link #coeff}
	 *
	 * @return {@link #coeff}
	 */
	public int getCoeff() {
		return coeff;
	}
}
