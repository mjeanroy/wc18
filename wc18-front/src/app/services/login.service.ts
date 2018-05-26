/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { of } from 'rxjs/internal/observable/of';
import { map, shareReplay, tap } from 'rxjs/operators';
import { AuthService } from '../auth/auth.service';
import { User } from '../models/user.model';
import { Principal } from '../models/principal.model';
import { UsersService } from './users.service';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private readonly _http: HttpClient;
  private readonly _usersService: UsersService;
  private readonly _authService: AuthService;
  private readonly _headerName: string;

  private _principal: Observable<User>;

  constructor(http: HttpClient, authService: AuthService, userService: UsersService) {
    this._http = http;
    this._authService = authService;
    this._usersService = userService;
    this._headerName = 'X-Auth-Token';
  }

  /**
   * Log user.
   *
   * @param {string} login User login.
   * @param {string} password User password.
   * @returns {Observable<Principal>} The login response.
   */
  login(login: string, password: string): Observable<Principal> {
    return this._http.post<User>('/api/login', {login, password}, {observe: 'response'})
      .pipe(
        map((response) => this._login(response)),
        tap(principal => this._onLogged(principal))
      );
  }

  /**
   * Logout user.
   *
   * @returns {Observable<any>} The logout response.
   */
  logout() {
    return this._http.post('/api/logout', null)
      .pipe(
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
      this._principal = this._usersService.me().pipe(shareReplay());
    }

    return this._principal;
  }

  private _login(response: HttpResponse<User>): Principal {
    const token: string = response.headers.get(this._headerName);
    const user: User = response.body;
    return {
      token,
      user,
    };
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
