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
