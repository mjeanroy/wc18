/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, Input } from '@angular/core';
import { Bet } from '../../models';

@Component({
  selector: 'dashboard-bets',
  templateUrl: './dashboard-bets.component.html',
  styleUrls: [
    './dashboard-card.scss',
    './dashboard-bets.component.scss',
  ],
})
export class DashboardBetsComponent {
  @Input("bets") bets: Bet[];
}
