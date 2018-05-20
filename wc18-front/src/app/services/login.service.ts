/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { map, tap } from 'rxjs/operators';
import { AuthService } from '../auth/auth.service';
import { User } from '../models/user.model';
import { Principal } from '../models/principal.model';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private readonly _http: HttpClient;
  private readonly _authService: AuthService;
  private readonly _headerName: string;

  private _principal: Principal;

  constructor(http: HttpClient, authService: AuthService) {
    this._http = http;
    this._authService = authService;
    this._headerName = 'X-Auth-Token';
  }

  login(login: string, password: string): Observable<Principal> {
    return this._http.post<User>('/api/login', {login, password}, {observe: 'response'})
      .pipe(
        map((response) => this._login(response)),
        tap(principal => this._onLogged(principal))
      );
  }

  logout() {
    return this._http.post('/api/logout', null)
      .pipe(
        tap(() => this._logout()),
      );
  }

  isLogged() {
    return this._authService.isLogged();
  }

  me() {
    return this._principal.user;
  }

  private _login(response: HttpResponse<User>): Principal {
    debugger;
    const token: string = response.headers.get(this._headerName);
    const user: User = response.body;
    return {token, user};
  }

  private _onLogged(principal: Principal) {
    this._principal = principal;
    this._authService.login(this._principal.token);
  }

  private _logout() {
    this._authService.logout();
  }
}
