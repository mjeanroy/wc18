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
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Match, Score } from '../models';

@Injectable({
  providedIn: 'root'
})
export class MatchesApiService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  /**
   * Find all matches.
   *
   * @returns {Observable<Match[]>} The asynchronous response.
   */
  findAll(): Observable<Match[]> {
    return this._http.get<Match[]>('/api/matches');
  }

  /**
   * Find all non locked matches.
   *
   * @returns {Observable<Match[]>} The asynchronous response.
   */
  findNonLocked(): Observable<Match[]> {
    return this._http.get<Match[]>('/api/matches', {
      params: {
        locked: 'false',
      }
    });
  }

  /**
   * Find all locked matches.
   *
   * @returns {Observable<Match[]>} The asynchronous response.
   */
  findLocked(): Observable<Match[]> {
    return this._http.get<Match[]>('/api/matches', {
      params: {
        locked: 'true',
      }
    });
  }

  create(match: Match): Observable<Match> {
    return this._http.post<Match>('/api/matches', match);
  }

  update(match: Match): Observable<Match> {
    return this._http.put<Match>(`/api/matches/${match.id}`, match);
  }

  updateScore(match: Match, score: Score): Observable<Match> {
    return this._http.put<Match>(`/api/matches/${match.id}/score`, score);
  }
}
