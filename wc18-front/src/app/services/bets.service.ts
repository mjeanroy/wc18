/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { map } from 'rxjs/operators';
import { BetsApiService, MatchesApiService, UsersApiService } from '../api';
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
  const map = new Map<string, Bet>(bets.map((bet: Bet): [string, Bet] =>
    [bet.match.id, bet]
  ));

  return matches.map((match: Match): Bet => (
    createBet(match, map, me)
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
    score: {
      score1: 0,
      score2: 0,
    },
  }
}
