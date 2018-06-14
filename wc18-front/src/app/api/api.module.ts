/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BetsApiService } from './bets.api.service';
import { LoginApiService } from './login.api.service';
import { MatchesApiService } from './matches.api.service';
import { TeamsApiService } from './teams.api.service';
import { UsersApiService } from './users.api.service';
import { LeaguesApiService } from './leagues.api.service';
import { MeApiService } from './me.api.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
  ],

  providers: [
    LeaguesApiService,
    TeamsApiService,
    MatchesApiService,
    BetsApiService,
    UsersApiService,
    MeApiService,
    LoginApiService,
  ],
})
export class ApiModule {
}
