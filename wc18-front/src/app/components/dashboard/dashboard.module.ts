/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule, MatIconModule } from '@angular/material';
import { ServicesModule } from '../../services';
import { BetsModule } from '../bets';
import { BetDaysComponent } from './bet-days.component';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    ServicesModule,
    BetsModule,
  ],

  declarations: [
    DashboardComponent,
    BetDaysComponent,
  ],

  exports: [
    DashboardComponent,
    BetDaysComponent,
  ],
})
export class DashboardModule {

}
