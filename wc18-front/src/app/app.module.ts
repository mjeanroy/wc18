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
import { PasswordFormModule } from './components/password/password-form.module';
import { RanksModule } from './components/ranks/ranks.module';
import { ResultsModule } from './components/results/results.module';

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
    ResultsModule,
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
