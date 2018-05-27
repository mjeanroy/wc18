/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

/**
 * Apply iteratee function to all input in array.
 *
 * @param {T[]} inputs Array inputs.
 * @param {(input: T) => void} iteratee The iteratee function.
 */
export function forEach<T>(inputs: T[], iteratee: (input: T) => void) {
  inputs.forEach(iteratee);
}
