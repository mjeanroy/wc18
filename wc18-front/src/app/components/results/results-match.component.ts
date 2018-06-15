/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Bet, Match } from '../../models';
import { LeaguesApiService, MatchesApiService, MeApiService } from '../../api';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { League } from '../../models/league.model';

@Component({
  selector: 'results-match',
  templateUrl: './results-match.component.html',
})
export class ResultsMatchComponent implements OnInit {

  private _matchApiService: MatchesApiService;
  private _meApiService: MeApiService;
  private _leaguesApiService: LeaguesApiService;

  leagues: League[];
  matches: Match[];
  league: League;

  idx: number;
  match: Match;
  bets: Bet[];
  loading: boolean;

  constructor(matchApiService: MatchesApiService, leaguesApiService: LeaguesApiService, meApiService: MeApiService) {
    this._matchApiService = matchApiService;
    this._leaguesApiService = leaguesApiService;
    this._meApiService = meApiService;
  }

  ngOnInit() {
    const observables = [
      this._matchApiService.findLocked(),
      this._meApiService.findLeagues(),
    ];

    forkJoin(...observables).subscribe((results) =>
      this._updateMatches(results[0], results[1])
    );
  }

  hasPrevious() {
    return this.bets && this.idx > 0;
  }

  hasNext() {
    return this.bets && this.idx < (this.matches.length - 1);
  }

  next() {
    if (this.hasNext()) {
      this._updateMatch(this.idx + 1);
    }
  }

  previous() {
    if (this.hasPrevious()) {
      this._updateMatch(this.idx - 1);
    }
  }

  fetchBets() {
    this.loading = true;
    this._leaguesApiService.getBets(this.league, this.match).subscribe((bets) => {
      this.bets = bets;
      this.loading = false;
    });
  }

  private _updateMatches(matches: Match[], leagues: League[]) {
    this.matches = matches;
    this.leagues = leagues;
    this.league = leagues[0];
    this._updateMatch(matches.length - 1);
  }

  private _updateMatch(idx: number) {
    this.idx = idx;
    this.match = this.matches[idx];
    this.fetchBets();
  }
}
