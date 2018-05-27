/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatProgressSpinnerModule,
} from '@angular/material';

import { ApiModule } from '../../api';
import { ServicesModule } from '../../services';

import { BetComponent } from './bet.component';
import { BetsCardComponent } from './bets-card.component';
import { BetsComponent } from './bets.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,

    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,

    ApiModule,
    ServicesModule,
  ],

  declarations: [
    BetsCardComponent,
    BetsComponent,
    BetComponent,
  ],

  exports: [
    BetsComponent,
    BetComponent,
  ],
})
export class BetsModule {
}
