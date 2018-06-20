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
import { Observable } from 'rxjs/internal/Observable';
import { of } from 'rxjs/internal/observable/of';
import { shareReplay, tap } from 'rxjs/operators';
import { LoginApiService, MeApiService, UsersApiService } from '../api';
import { AuthService } from '../auth';
import { Logger } from '../log';
import { User, Principal } from '../models';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private readonly _logger: Logger;
  private readonly _loginApiService: LoginApiService;
  private readonly _meApiService: MeApiService;
  private readonly _authService: AuthService;

  private _principal: Observable<User>;
  private _admin: boolean;

  constructor(logger: Logger, loginApiService: LoginApiService, authService: AuthService, meApiService: MeApiService) {
    this._logger = logger;
    this._loginApiService = loginApiService;
    this._authService = authService;
    this._meApiService = meApiService;
    this._admin = false;
  }

  /**
   * Log user.
   *
   * @param {string} login User login.
   * @param {string} password User password.
   * @returns {Observable<Principal>} The login response.
   */
  login(login: string, password: string): Observable<Principal> {
    this._logger.debug('Processing login');
    return this._loginApiService.login(login, password).pipe(
      tap(principal => this._onLogged(principal))
    );
  }

  /**
   * Logout user.
   *
   * @returns {Observable<any>} The logout response.
   */
  logout() {
    this._logger.debug('Processing logout');
    return this._loginApiService.logout().pipe(
      tap(() => this._logout()),
    );
  }

  /**
   * Check if a user is currently authenticated.
   *
   * @returns {boolean} `true` if authenticated user exists, `false` otherwise.
   */
  isLogged() {
    return this._authService.isLogged();
  }

  /**
   * Check if current authenticated user is an administrator.
   *
   * @returns {boolean} `true` if current authenticated user is an administrator, `false` otherwise.
   */
  isAdmin() {
    return this.isLogged() && this._admin;
  }

  /**
   * Get the current logged user.
   *
   * @returns {Observable<User>} Logged user.
   */
  me() {
    if (this.isLogged() && !this._principal) {
      this._logger.debug('Trying to get authenticated user but it is not available yet, querying from API');
      this._principal = this._meApiService.me().pipe(
        shareReplay(),
        tap((principal) => this._setPrincipal(principal))
      );
    }

    return this._principal;
  }

  private _onLogged(principal: Principal) {
    this._setPrincipal(principal.user);
    this._authService.login(principal.token);
  }

  private _setPrincipal(user: User) {
    this._principal = of(user);
    this._admin = user.role === 'ADMIN';
  }

  private _logout() {
    this._authService.logout();
    this._principal = null;
  }
}
