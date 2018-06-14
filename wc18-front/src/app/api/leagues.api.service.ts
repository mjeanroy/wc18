/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { League } from '../models/league.model';
import { Rank, User } from '../models';

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
