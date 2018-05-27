/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Team } from '../models';

@Injectable({
  providedIn: 'root'
})
export class TeamsApiService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  /**
   * Find all teams.
   *
   * @returns {Observable<Team[]>} The asynchronous response.
   */
  findAll(): Observable<Team[]> {
    return this._http.get<Team[]>('/api/teams');
  }
}
