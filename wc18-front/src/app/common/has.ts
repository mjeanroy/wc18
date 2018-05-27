/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

/**
 * Check if input object has given key.
 *
 * @param {{}} input The input object.
 * @param {string} key The key name.
 * @returns {boolean} `true` if object has given property, `false` otherwise.
 */
export function has(input: { }, key: string): boolean {
  return Object.prototype.hasOwnProperty.call(input, key);
}
