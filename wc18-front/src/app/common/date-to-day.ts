/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

/**
 * Translate a date to the corresponding day in US format.
 *
 * @param {Date|string} date The date (as a `Date` object or as an ISO `string` value).
 * @returns {string} The day, format is: `yyyy-mm-dd`.
 */
export function dateToDay(date: Date | string): string {
  const isoString = date instanceof Date ? date.toISOString() : date;
  return isoString.slice(0, 10);
}
