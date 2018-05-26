/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { of } from 'rxjs/internal/observable/of';
import { shareReplay, tap } from 'rxjs/operators';
import { LoginApiService, UsersApiService } from '../api';
import { AuthService } from '../auth';
import { Logger } from '../log';
import { User, Principal } from '../models';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private readonly _logger: Logger;
  private readonly _loginApiService: LoginApiService;
  private readonly _usersApiService: UsersApiService;
  private readonly _authService: AuthService;

  private _principal: Observable<User>;

  constructor(logger: Logger, loginApiService: LoginApiService, authService: AuthService, usersApiService: UsersApiService) {
    this._logger = logger;
    this._loginApiService = loginApiService;
    this._authService = authService;
    this._usersApiService = usersApiService;
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
   * Get the current logged user.
   *
   * @returns {Observable<User>} Logged user.
   */
  me() {
    if (this.isLogged() && !this._principal) {
      this._logger.debug('Trying to get authenticated user but it is not available yet, querying from API');
      this._principal = this._usersApiService.me().pipe(shareReplay());
    }

    return this._principal;
  }

  private _onLogged(principal: Principal) {
    this._principal = of(principal.user);
    this._authService.login(principal.token);
  }

  private _logout() {
    this._authService.logout();
    this._principal = null;
  }
}
