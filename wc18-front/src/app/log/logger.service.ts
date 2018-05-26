/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Logger {
  /**
   * Log message with level `INFO`.
   *
   * @param {string} msg Message to log.
   * @returns {void}
   */
  info(msg: string) {
    console.info(`[INFO] ${msg}`);
  }

  /**
   * Log message with level `DEBUG`.
   *
   * @param {string} msg Message to log.
   * @returns {void}
   */
  debug(msg: string) {
    console.debug(`[DEBUG] ${msg}`);
  }
}
