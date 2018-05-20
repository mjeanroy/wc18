/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatToolbarModule,
} from '@angular/material';

import { appRoutes } from './routing/app-routes';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavBarComponent } from './components/navbar/navbar.component';
import { MatchesComponent } from './components/matches/matches.component';

import { AuthStorage } from './auth/auth.storage';
import { AuthService } from './auth/auth.service';
import { AuthInterceptor } from './auth/auth.interceptor';

import { LoginService } from './services/login.service';
import { SnackbarService } from './services/snackbar.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavBarComponent,
    MatchesComponent,
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
    MatInputModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatToolbarModule,
  ],

  providers: [
    // Auth
    AuthStorage,
    AuthService,
    AuthInterceptor,

    // App
    LoginService,
    SnackbarService,
  ],

  bootstrap: [
    AppComponent,
  ],
})
export class AppModule {
}
