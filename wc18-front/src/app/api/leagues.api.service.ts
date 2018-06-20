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
import { League } from '../models/league.model';
import { Bet, Match, Rank, User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class LeaguesApiService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  /**
   * Get all leagues.
   *
   * @returns {Observable<League[]>} The response.
   */
  findAll(): Observable<League[]> {
    return this._http.get<League[]>('/api/leagues');
  }

  /**
   * Get all ranks of given league.
   *
   * @param {League} league The league.
   * @returns {Observable<Rank[]>} The response.
   */
  getRanks(league: League): Observable<Rank[]> {
    return this._http.get<Rank[]>(`/api/leagues/${league.id}/ranks`);
  }

  getBets(league: League, match: Match) {
    return this._http.get<Bet[]>(`/api/leagues/${league.id}/matches/${match.id}/bets`);
  }

  /**
   * Create league.
   *
   * @param {League} league The league to create.
   * @returns {Observable<League>} The response.
   */
  create(league: League) {
    return this._http.post<League>('/api/leagues', league);
  }

  /**
   * Add user to league.
   *
   * @param {League} league The league.
   * @param {User} league The user to add.
   * @returns {Observable<any>} The response.
   */
  addUser(league: League, user: User) {
    return this._http.post<any>(`/api/leagues/${league.id}/users/${user.id}`, null);
  }
}
