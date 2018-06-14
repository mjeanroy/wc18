/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import localeFr from '@angular/common/locales/fr';
import { MatIconModule, MatIconRegistry } from '@angular/material';
import { BrowserModule, DomSanitizer } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { RouterModule } from '@angular/router';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ApiModule } from './api';

import { LoginModule } from './components/login';
import { NavbarModule } from './components/navbar/navbar.module';
import { BetsModule } from './components/bets';
import { DashboardModule } from './components/dashboard';

import { routes } from './routing/routes';
import { AppComponent } from './app.component';
import { AdminModule } from './components/admin/admin.module';
import {UserFormComponent} from "./components/admin/users/user-form.component";
import { PasswordFormModule } from './components/password/password-form.module';
import { RanksModule } from './components/ranks/ranks.module';

registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [
    AppComponent,
  ],

  imports: [
    // @angular
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatIconModule,
    RouterModule.forRoot(routes),

    // @bootstrap
    NgbDropdownModule.forRoot(),

    // app components
    ApiModule,
    LoginModule,
    PasswordFormModule,
    NavbarModule,
    BetsModule,
    DashboardModule,
    RanksModule,
    AdminModule,
  ],

  providers: [
    { provide: LOCALE_ID, useValue: 'fr' },
  ],

  bootstrap: [
    AppComponent,
  ],
})
export class AppModule {
  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon('wc2018', sanitizer.bypassSecurityTrustResourceUrl('assets/svg/wc2018.svg'));
    iconRegistry.addSvgIconSetInNamespace('countries', sanitizer.bypassSecurityTrustResourceUrl('assets/svg/countries.svg'));
  }
}
