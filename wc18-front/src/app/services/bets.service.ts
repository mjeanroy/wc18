/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Bet } from '../models/bet.model';

@Injectable({
  providedIn: 'root'
})
export class BetsService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  findAll(): Observable<Bet[]> {
    return this._http.get<Bet[]>('/api/me/bets');
  }

  saveOrUpdate(bet: Bet): Observable<Bet> {
    return this._http.post<Bet>('/api/me/bets', bet);
  }
}
