/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Login, Passwords, User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class UsersApiService {

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
