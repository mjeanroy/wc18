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
