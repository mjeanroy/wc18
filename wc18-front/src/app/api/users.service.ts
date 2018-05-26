/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { User } from '../models';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

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
}
