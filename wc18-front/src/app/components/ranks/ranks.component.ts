/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Rank } from '../../models';
import { League } from '../../models/league.model';
import { LeaguesApiService, MeApiService } from '../../api';

@Component({
  selector: 'ranks',
  templateUrl: './ranks.component.html',
})
export class RanksComponent implements OnInit {

  private _meApiService: MeApiService;
  private _leaguesApiService: LeaguesApiService;

  leagues: League[];
  league: League;
  idx: number;

  ranks: Rank[];

  constructor(meApiService: MeApiService, leaguesApiService: LeaguesApiService) {
    this._meApiService = meApiService;
    this._leaguesApiService = leaguesApiService;
  }

  ngOnInit() {
    this._meApiService.findLeagues().subscribe((leagues) => {
      this.leagues = leagues;
      this._setLeague(0);
    });
  }

  hasPrevious() {
    return this.leagues && this.idx > 0;
  }

  hasNext() {
    return this.leagues && this.idx < (this.leagues.length - 1);
  }

  previous() {
    if (this.hasPrevious()) {
      this._goAt(this.idx - 1);
    }
  }

  next() {
    if (this.hasNext()) {
      this._goAt(this.idx + 1);
    }
  }

  private _goAt(idx) {
    this._setLeague(idx);
  }

  private _setLeague(idx) {
    this.idx = idx;
    this.league = this.leagues[idx];

    this.ranks = null;
    this._leaguesApiService.getRanks(this.league).subscribe((ranks) =>
      this.ranks = ranks
    );
  }
}
