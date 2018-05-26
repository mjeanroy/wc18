/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Logger } from './logger.service';

@NgModule({
  imports: [
    CommonModule,
  ],

  providers: [
    Logger,
  ],
})
export class LogModule {

}
