/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule, MatGridListModule } from '@angular/material';
import { BetsModule } from '../bets';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatGridListModule,
    BetsModule,
  ],

  declarations: [
    DashboardComponent,
  ],

  exports: [
    DashboardComponent,
  ],
})
export class DashboardModule {

}
