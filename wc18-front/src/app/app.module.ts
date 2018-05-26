/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import {
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatFormFieldModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatToolbarModule,
} from '@angular/material';

import { LogModule } from './log';
import { ApiModule } from './api';
import { AuthModule } from './auth/auth.module';

import { BetsCardComponent } from './components/bets/bets-card.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import { appRoutes } from './routing/app-routes';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavBarComponent } from './components/navbar/navbar.component';
import { BetsComponent } from './components/bets/bets.component';
import { BetComponent } from './components/bets/bet.component';

import { SnackbarService } from './services/snackbar.service';
import { LoginService } from './services/login.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavBarComponent,
    DashboardComponent,
    BetsCardComponent,
    BetsComponent,
    BetComponent,
  ],

  imports: [
    // @angular
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),

    // @angular/material
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatToolbarModule,

    // app
    LogModule,
    ApiModule,
    AuthModule,
  ],

  providers: [
    // App
    SnackbarService,
    LoginService,
  ],

  bootstrap: [
    AppComponent,
  ],
})
export class AppModule {
}
