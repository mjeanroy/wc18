/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, Input, OnChanges } from '@angular/core';
import { dateToDay, groupBy, keys, reduce } from '../../common';
import { Bet } from '../../models';

@Component({
  selector: 'app-dashboard-calendar',
  templateUrl: './dashboard-calendar.component.html',
  styleUrls: [
    './dashboard-card.scss',
    './dashboard-calendar.component.scss',
  ],
})
export class DashboardCalendarComponent implements OnChanges {

  @Input() bets: Bet[];

  idx: number;
  days: { date: string, bets: Bet[] }[];
  current: { date: string, bets: Bet[] };

  ngOnChanges(changes) {
    if (changes.bets) {
      this._onUpdateBets();
    }
  }

  /**
   * Go to the next day.
   *
   * @returns {void}
   */
  nextDay() {
    this.idx++;
    this.current = this.days[this.idx];
  }

  /**
   * Go to the previous day.
   *
   * @returns {void}
   */
  previousDay() {
    this.idx--;
    this.current = this.days[this.idx];
  }

  /**
   * Check if a next day is available.
   *
   * @returns {boolean} `true` if a next day is available, `false` otherwise.
   */
  hasNext() {
    return this.idx < (this.days.length - 1);
  }

  /**
   * Check if a previous day is available.
   *
   * @returns {boolean} `true` if a previous day is available, `false` otherwise.
   */
  hasPrevious() {
    return this.idx > 0;
  }

  private _onUpdateBets() {
    if (!this.bets) {
      this._init();
    } else {
      this._update();
    }
  }

  private _update() {
    const byDays = groupBy(this.bets, (bet) => dateToDay(bet.match.date));
    const days = keys(byDays).sort((d1, d2) => d1.localeCompare(d2));

    this.days = reduce(days, [], (acc, day) => (
      acc.concat({
        date: day,
        bets: byDays[day],
      })
    ));

    this.idx = 0;
    this.current = this.days[0];
  }

  private _init() {
    this.days = [];
    this.current = null;
    this.idx = 0;
  }
}
