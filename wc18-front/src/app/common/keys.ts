/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

/**
 * Get all object own properties.
 *
 * @param {{}} input The input object.
 * @returns {string[]} Object own properties.
 */
export function keys(input: { }): string[] {
  return Object.keys(input);
}
