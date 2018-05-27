/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { RouterModule } from '@angular/router';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginModule } from './components/login';
import { NavbarModule } from './components/navbar/navbar.module';
import { BetsModule } from './components/bets';
import { DashboardModule } from './components/dashboard';

import { routes } from './routing/routes';
import { AppComponent } from './app.component';

registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [
    AppComponent,
  ],

  imports: [
    // @angular
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),

    // @bootstrap
    NgbDropdownModule.forRoot(),

    // app components
    LoginModule,
    NavbarModule,
    BetsModule,
    DashboardModule,
  ],

  providers: [
    { provide: LOCALE_ID, useValue: 'fr' },
  ],

  bootstrap: [
    AppComponent,
  ],
})
export class AppModule {
}
