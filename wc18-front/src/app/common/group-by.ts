/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { has } from './has';
import { reduce } from './reduce';

/**
 * Group inputs in arrays to an object where each property returned by the indexer
 * function is mapped an array containing each input corresponding values.
 *
 * @param {T[]} inputs Array of inputs.
 * @param {(input: T) => string} accumulate The indexer function.
 * @returns {{}} The result map.
 */
export function groupBy<T>(inputs: T[], accumulate: (input: T) => string) {
  return reduce(inputs, {}, (acc, input) => reducer(input, accumulate, acc));
}

function reducer<T>(input: T, indexer: (input: T) => string, results: { }) {
  const key = indexer(input);
  if (!has(results, key)) {
    results[key] = [];
  }

  results[key].push(input);

  return results;
}
