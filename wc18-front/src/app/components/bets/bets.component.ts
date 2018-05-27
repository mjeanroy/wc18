/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, Input } from '@angular/core';
import { Bet } from '../../models';
import { BetsService } from '../../services';

@Component({
  selector: 'app-bets',
  templateUrl: './bets.component.html',
  styleUrls: [
    './bets.component.css',
  ]
})
export class BetsComponent {

  private readonly _betsService: BetsService;

  @Input("bets") bets: Bet[];

  constructor(betsService: BetsService) {
    this._betsService = betsService;
  }
}
