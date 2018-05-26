import { HttpClient } from '@angular/common/http';

/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';
import { Principal, User } from '../models';

@Injectable({
  providedIn: 'root',
})
export class LoginApiService {

  private readonly _http: HttpClient;
  private readonly _headerName: string;

  constructor(http: HttpClient) {
    this._http = http;
    this._headerName = 'X-Auth-Token';
  }

  /**
   * Process login with given login/password.
   *
   * @param login The login.
   * @param password The password.
   * @returns {Observable<Principal>} The login response.
   */
  login(login, password): Observable<Principal> {
    return this._http.post<User>('/api/login', {login, password}, {observe: 'response'})
      .pipe(
        map(response => ({
          token: response.headers.get(this._headerName),
          user: response.body,
        }))
      );
  }

  /**
   * Process logout and returns response.
   *
   * @returns {Observable<any>} The response.
   */
  logout(): Observable<any> {
    return this._http.post('/api/logout', null);
  }
}
