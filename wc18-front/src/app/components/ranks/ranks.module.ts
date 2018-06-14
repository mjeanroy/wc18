/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatIconModule, MatProgressSpinnerModule } from '@angular/material';
import { ServicesModule } from '../../services';
import { RanksComponent } from './ranks.component';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    ServicesModule,
  ],

  declarations: [
    RanksComponent,
  ],

  exports: [
    RanksComponent,
  ],
})
export class RanksModule {
}
