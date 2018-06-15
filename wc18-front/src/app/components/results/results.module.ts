/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatFormFieldModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatSelectModule
} from '@angular/material';
import { ServicesModule } from '../../services';
import { ResultsMatchComponent } from './results-match.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    ServicesModule,
  ],

  declarations: [
    ResultsMatchComponent,
  ],

  exports: [
    ResultsMatchComponent,
  ],
})
export class ResultsModule {
}
