/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
