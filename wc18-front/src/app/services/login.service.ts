/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  login(login: string, password: string) {
    return this._http.post('/api/login', {
      login,
      password,
    });
  }
}
