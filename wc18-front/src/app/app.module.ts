/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { RouterModule } from '@angular/router';
import { GuardsModule } from './guards/guards.module';

import { LoginModule } from './components/login';
import { NavbarModule } from './components/navbar/navbar.module';
import { BetsModule } from './components/bets';
import { DashboardModule } from './components/dashboard';

import { routes } from './routing/routes';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
  ],

  imports: [
    // @angular
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),

    // app components
    LoginModule,
    NavbarModule,
    BetsModule,
    DashboardModule,
  ],

  providers: [
  ],

  bootstrap: [
    AppComponent,
  ],
})
export class AppModule {
}
