/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

/**
 * Reduce array of inputs to a single final value.
 *
 * @param {T[]} inputs Array of inputs.
 * @param {(acc: U, input: T) => U} reducer The reducer function.
 * @param {U} acc The accumulator initial value.
 * @returns {string} The final results.
 */
export function reduce<T, U>(inputs: T[], acc: U, reducer: (acc: U, input: T) => U): U {
  return inputs.reduce(reducer, acc);
}
