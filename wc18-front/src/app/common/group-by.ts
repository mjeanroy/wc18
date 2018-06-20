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
