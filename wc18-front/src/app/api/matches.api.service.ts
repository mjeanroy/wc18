/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Match } from '../models';

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

  update(match: Match): Observable<Match> {
    return this._http.put<Match>(`/api/matches/${match.id}`, match);
  }
}
