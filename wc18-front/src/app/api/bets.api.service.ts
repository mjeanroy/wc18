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
import { HttpClient } from '@angular/common/http';
import { Bet, User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class BetsApiService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  /**
   * Get all bets of current authenticated user.
   *
   * @param {boolean} locked Flag to look for locked or non-locked matches only.
   * @returns {Observable<Bet[]>} The asynchronous response.
   */
  findAll(locked: boolean): Observable<Bet[]> {
    return this._http.get<Bet[]>('/api/me/bets', {
      params: {
        locked: locked.toString(),
      },
    });
  }

  /**
   * Create, or update, bet for current authenticated user.
   *
   * @param {Bet} bet The bet to save.
   * @returns {Observable<Bet>} The asynchronous response.
   */
  saveOrUpdate(bet: Bet): Observable<Bet> {
    return this._http.post<Bet>('/api/me/bets', bet);
  }

  save(user: User, bet: Bet): Observable<Bet> {
    return this._http.post<Bet>(`/api/users/${user.id}/bets`, bet);
  }
}
