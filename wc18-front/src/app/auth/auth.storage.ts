/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthStorage {
  private readonly _name: string;
  private readonly _storage: Storage;

  constructor() {
    this._name = 'wc18-token';
    this._storage = localStorage;
  }

  getToken(): string {
    return this._storage.getItem(this._name);
  }

  setToken(value: string) {
    this._storage.setItem(this._name, value);
  }

  clear() {
    this._storage.removeItem(this._name);
  }
}
