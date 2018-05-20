/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import {AuthStorage} from "./auth.storage";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly _storage: AuthStorage;
  private _token: string;

  constructor(storage: AuthStorage) {
    this._storage = storage;
    this._token = this._storage.getToken();
  }

  isLogged() {
    return this._token !== null;
  }

  token() {
    return this._token;
  }

  login(token: string) {
    this._storage.setToken(token);
    this._token = token;
  }

  logout() {
    this._storage.clear();
    this._token = null;
  }
}
