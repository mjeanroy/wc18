/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BetsApiService } from './bets.api.service';
import { MatchesApiService } from './matches.api.service';
import { UsersApiService } from './users.api.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
  ],

  providers: [
    MatchesApiService,
    BetsApiService,
    UsersApiService,
  ],
})
export class ApiModule {
}
