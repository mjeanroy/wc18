/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Bet } from '../../models';
import { BetsService } from '../../services';

@Component({
  selector: 'app-bets-card',
  templateUrl: './bets-card.component.html',
  styleUrls: [
    './bets-card.component.css',
  ],
})
export class BetsCardComponent implements OnInit {

  private readonly _betsService: BetsService;

  bets: Bet[];

  constructor(betsService: BetsService) {
    this._betsService = betsService;
  }

  ngOnInit() {
    this._betsService.getBets().subscribe((bets) =>
      this.bets = bets
    );
  }
}
