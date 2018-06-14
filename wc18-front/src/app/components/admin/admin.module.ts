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
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatSelectModule,
  MatTabsModule
} from '@angular/material';
import { AdminComponent } from './admin.component';
import { UsersComponent } from "./users/users.component";
import { UserFormComponent } from './users/user-form.component';
import { LeaguesComponent } from './leagues/leagues.component';
import { LeagueFormComponent } from './leagues/league-form.component';
import { LeagueUserFormComponent } from './leagues/league-user-form.component';
import { PipesModule } from '../../pipes/pipes.module';
// import { LeagueUserFormComponent } from './leagues/league-user-form.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
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

    LeaguesComponent,
    LeagueFormComponent,
    LeagueUserFormComponent,
  ],

  entryComponents: [
    UserFormComponent,
    LeagueFormComponent,
    LeagueUserFormComponent,
  ],

  exports: [
    AdminComponent,
  ],
})
export class AdminModule {

}
