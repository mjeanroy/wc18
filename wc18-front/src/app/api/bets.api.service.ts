/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
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
