/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Login, User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class UsersApiService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  /**
   * Get all users.
   *
   * @returns {Observable<User[]>} The users response.
   */
  findAll(): Observable<User[]> {
    return this._http.get<User[]>("/api/users");
  }

  /**
   * Create new user.
   *
   * @returns {Observable<User>} The new user response.
   */
  create(account: Login): Observable<User> {
    return this._http.post<User>("/api/users", account);
  }
}
