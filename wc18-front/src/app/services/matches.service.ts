/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/internal/Observable";
import {Match} from "../models/match.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  private _http: HttpClient;

  constructor(http: HttpClient) {
    this._http = http;
  }

  findAll(): Observable<Match[]> {
    return this._http.get<Match[]>('/api/matches');
  }
}
