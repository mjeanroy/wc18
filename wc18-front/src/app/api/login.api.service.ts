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
import { HttpClient } from '@angular/common/http';
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
