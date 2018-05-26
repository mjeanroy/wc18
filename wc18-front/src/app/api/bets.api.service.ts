/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Bet } from '../models';

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
   * @returns {Observable<Bet[]>} The asynchronous response.
   */
  findAll(): Observable<Bet[]> {
    return this._http.get<Bet[]>('/api/me/bets');
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
}
