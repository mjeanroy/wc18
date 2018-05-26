/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
}
