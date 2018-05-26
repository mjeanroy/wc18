/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { MatchesService } from '../../services/matches.service';
import { BetsService } from '../../services/bets.service';
import { Bet, Match, User } from '../../models';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-bets',
  templateUrl: './bets.component.html',
  styleUrls: [
    './bets.component.css',
  ]
})
export class BetsComponent implements OnInit {

  private _loginService: LoginService;
  private _matchesService: MatchesService;
  private _betsService: BetsService;

  bets: Bet[];

  constructor(loginService: LoginService, matchesService: MatchesService, betsService: BetsService) {
    this._loginService = loginService;
    this._matchesService = matchesService;
    this._betsService = betsService;
  }

  ngOnInit() {
    forkJoin(this._matchesService.findAll(), this._betsService.findAll(), this._loginService.me()).subscribe(
      (results) => this._createBets(results[0], results[1], results[2])
    );
  }

  private _createBets(matches: Match[], bets: Bet[], me: User) {
    // Index bets by match id.
    const map = new Map<string, Bet>(bets.map((bet: Bet): [string, Bet] =>
      [bet.match.id, bet]
    ));

    this.bets = matches.map((match: Match): Bet => (
      map.get(match.id) || ({
        match,

        id: null,
        date: new Date(),
        user: me,
        score: {
          score1: 0,
          score2: 0,
        },
      })
    ));
  }
}
