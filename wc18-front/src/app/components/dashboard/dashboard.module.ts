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
import { DashboardBetsComponent } from './dashboard-bets.component';
import { DashboardMatchesComponent } from './dashboard-matches.component';
import { DashboardComponent } from './dashboard.component';
import { DashboardCalendarComponent } from './dashboard-calendar.component';

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
    DashboardCalendarComponent,
    DashboardBetsComponent,
    DashboardMatchesComponent,
  ],

  exports: [
    DashboardComponent,
  ],
})
export class DashboardModule {

}
