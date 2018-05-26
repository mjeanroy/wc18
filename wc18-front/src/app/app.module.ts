/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
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

import { ApiModule } from './api';
import { BetsCardComponent } from './components/bets/bets-card.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

import { appRoutes } from './routing/app-routes';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavBarComponent } from './components/navbar/navbar.component';
import { BetsComponent } from './components/bets/bets.component';
import { BetComponent } from './components/bets/bet.component';

import { AuthStorage } from './auth/auth.storage';
import { AuthService } from './auth/auth.service';
import { AuthInterceptor } from './auth/auth.interceptor';

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
    HttpClientModule,
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
    ApiModule,
  ],

  providers: [
    // Auth
    AuthStorage,
    AuthService,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },

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
