/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LogModule } from '../log';
import { IsLoggedGuard } from './is-logged.guard';
import { IsNotLoggedGuard } from './is-not-logged.guard';

@NgModule({
  imports: [
    CommonModule,
    LogModule,
  ],

  providers: [
    IsLoggedGuard,
    IsNotLoggedGuard,
  ],
})
export class GuardsModule {
}
