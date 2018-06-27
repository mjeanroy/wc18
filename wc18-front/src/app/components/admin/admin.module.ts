/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mickael Jeanroy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  MatButtonModule,
  MatDatepickerModule,
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatNativeDateModule,
  MatSelectModule,
  MatTabsModule
} from '@angular/material';
import { AdminComponent } from './admin.component';
import { UsersComponent } from './users/users.component';
import { UserFormComponent } from './users/user-form.component';
import { LeaguesComponent } from './leagues/leagues.component';
import { LeagueFormComponent } from './leagues/league-form.component';
import { LeagueUserFormComponent } from './leagues/league-user-form.component';
import { PipesModule } from '../../pipes/pipes.module';
import { MatchesComponent } from './matches/matches.component';
import { MatchEditFormComponent } from './matches/match-edit-form.component';
import { UserBetComponent } from './users/user-bet.component';
import { MatchNewFormComponent } from './matches/match-new-form.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTabsModule,

    PipesModule,
  ],

  declarations: [
    AdminComponent,

    UsersComponent,
    UserFormComponent,
    UserBetComponent,

    LeaguesComponent,
    LeagueFormComponent,
    LeagueUserFormComponent,

    MatchesComponent,
    MatchNewFormComponent,
    MatchEditFormComponent,
  ],

  entryComponents: [
    UserFormComponent,
    LeagueFormComponent,
    LeagueUserFormComponent,
    MatchEditFormComponent,
    MatchNewFormComponent,
    UserBetComponent,
  ],

  exports: [
    AdminComponent,
  ],
})
export class AdminModule {

}
