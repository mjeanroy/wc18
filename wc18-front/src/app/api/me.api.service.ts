/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Passwords, User } from '../models';
import { League } from '../models/league.model';

@Injectable({
  providedIn: 'root'
})
export class MeApiService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  /**
   * Get current authenticated user.
   *
   * @returns {Observable<User>} The authenticated response.
   */
  me(): Observable<User> {
    return this._http.get<User>('/api/me');
  }

  /**
   * Update user passwords.
   *
   * @param {Passwords} passwords The password form.
   * @returns {Observable<any>} The response.
   */
  updatePassword(passwords: Passwords): Observable<any> {
    return this._http.post('/api/me/password', passwords);
  }

  /**
   * Find leagues of given user.
   *
   * @returns {Observable<League[]>} The response.
   */
  findLeagues(): Observable<League[]> {
    return this._http.get<League[]>('/api/me/leagues');
  }
}
