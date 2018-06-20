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

import { Component, Input, OnChanges } from '@angular/core';
import { dateToDay, groupBy, keys, reduce } from '../../common';
import { Bet } from '../../models';

@Component({
  selector: 'app-dashboard-matches',
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
