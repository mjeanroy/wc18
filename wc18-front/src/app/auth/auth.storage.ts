/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Logger } from '../log';

@Injectable({
  providedIn: 'root'
})
export class AuthStorage {

  private readonly _name: string;
  private readonly _storage: Storage;
  private readonly _logger: Logger;

  constructor(logger: Logger) {
    this._name = 'wc18-token';
    this._storage = localStorage;
    this._logger = logger;
  }

  /**
   * Get stored token, or returns `null` if token does not exist.
   *
   * @returns {string} The token.
   */
  getToken(): string {
    this._logger.debug('Get token from persistent storage');
    return this._storage.getItem(this._name);
  }

  /**
   * Store token in persistent storage.
   *
   * @param {string} value Token value.
   * @returns {void}
   */
  setToken(value: string) {
    this._logger.debug(`Storing token to persistent storage`);
    this._storage.setItem(this._name, value);
  }

  /**
   * Clear persistent storage.
   *
   * @returns {void}
   */
  clear() {
    this._logger.debug('Remove token to persistent storage');
    this._storage.removeItem(this._name);
  }
}
