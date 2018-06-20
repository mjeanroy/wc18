/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { map } from 'rxjs/operators';
import { BetsApiService, MatchesApiService } from '../api';
import { Bet, Match, User } from '../models';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class BetsService {

  private _betsApiService: BetsApiService;
  private _matchesApiService: MatchesApiService;
  private _loginService: LoginService;

  constructor(betsApiService: BetsApiService, matchesApiService: MatchesApiService, loginService: LoginService) {
    this._betsApiService = betsApiService;
    this._matchesApiService = matchesApiService;
    this._loginService = loginService;
  }

  /**
   * Get bets of authenticated user.
   *
   * @returns {Observable<Bet[]>} The bet response.
   */
  getBets(): Observable<Bet[]> {
    const locked = false;

    const observables = [
      this._matchesApiService.findNonLocked(),
      this._betsApiService.findAll(locked),
      this._loginService.me(),
    ];

    return forkJoin(...observables).pipe(
      map((results) => createBets(results[0], results[1], results[2]))
    );
  }
}

function createBets(matches: Match[], bets: Bet[], me: User) {
  // Index bets by match id.
  const mapOfBets = new Map<string, Bet>(bets.map((bet: Bet): [string, Bet] =>
    [bet.match.id, bet]
  ));

  return matches.map((match: Match): Bet => (
    createBet(match, mapOfBets, me)
  ));
}

function createBet(match: Match, bets: Map<string, Bet>, me: User) {
  const matchId = match.id;
  if (bets.has(matchId)) {
    return bets.get(matchId);
  }

  return {
    match,

    id: null,
    date: new Date(),
    user: me,
    result: 'UNAVAILABLE',
    point: 0,
    score: {
      score1: 0,
      score2: 0,
    },
  };
}
