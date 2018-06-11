/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, Input, OnChanges } from '@angular/core';
import { dateToDay, groupBy, keys, reduce } from '../../common';
import { Bet } from '../../models';

@Component({
  selector: 'dashboard-matches',
  templateUrl: './dashboard-matches.component.html',
  styleUrls: [
    './dashboard-card.scss',
    './dashboard-matches.component.scss',
  ],
})
export class DashboardMatchesComponent implements OnChanges {

  @Input() bets: Bet[];

  idx: number;
  current: Bet;

  ngOnChanges(changes) {
    if (changes.bets) {
      this._onBetsUpdated();
    }
  }

  next() {
    this._goAt(this.idx + 1);
  }

  previous() {
    this._goAt(this.idx - 1);
  }

  hasNext() {
    if (!this.bets) {
      return false;
    }

    return this.idx < (this.bets.length - 1);
  }

  hasPrevious() {
    if (!this.bets) {
      return false;
    }

    return this.idx > 0;
  }

  private _goAt(idx) {
    if (this.bets) {
      this.idx = idx;
      this.current = this.bets[idx];
    }
  }

  private _onBetsUpdated() {
    if (this.bets) {
      this._update();
    } else {
      this._init();
    }
  }

  private _update() {
    this.idx = 0;
    this.current = this.bets[0];
  }

  private _init() {
    this.idx = 0;
    this.current = null;
  }
}
