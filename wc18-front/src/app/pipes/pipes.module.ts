/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapPipe } from './map.pipe';
import { JoinPipe } from './join.pipe';

@NgModule({
  imports: [
    CommonModule,
  ],

  declarations: [
    MapPipe,
    JoinPipe,
  ],

  exports: [
    MapPipe,
    JoinPipe,
  ]
})
export class PipesModule {
}
