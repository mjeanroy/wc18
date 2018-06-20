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
import { AuthStorage } from './auth.storage';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly _storage: AuthStorage;
  private _token: string;

  constructor(storage: AuthStorage) {
    this._storage = storage;
    this._token = this._storage.getToken();
  }

  /**
   * Check if current authentication token exists, meaning that a user
   * is authenticated.
   *
   * @returns {boolean} `true` if authentication token is available, `false` otherwise.
   */
  isLogged() {
    return this._token !== null;
  }

  /**
   * Get current authentication token.
   *
   * @returns {string} The token, `null` if it is not available.
   */
  token() {
    return this._token;
  }

  /**
   * Log user, i.e store authentication token.
   *
   * @param {string} token The authentication token.
   * @returns {void}
   */
  login(token: string) {
    this._storage.setToken(token);
    this._token = token;
  }

  /**
   * Logout user, i.e clear authentication token.
   *
   * @returns {void}
   */
  logout() {
    this._storage.clear();
    this._token = null;
  }
}
