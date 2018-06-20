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

import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login';
import { DashboardComponent } from '../components/dashboard';
import { BetsCardComponent } from '../components/bets';
import { IsLoggedGuard, IsNotLoggedGuard } from '../guards';
import { ADMIN, BETS, HOME, LOGIN, PASSWORD, RANKS, RESULTS } from './paths';
import { AdminComponent } from "../components/admin/admin.component";
import { PasswordFormComponent } from '../components/password/password-form.component';
import { RanksComponent } from '../components/ranks/ranks.component';
import { ResultsMatchComponent } from '../components/results/results-match.component';

export const routes: Routes = [
  {
    path: LOGIN,
    component: LoginComponent,
    canActivate: [
      IsNotLoggedGuard,
    ],
  },

  {
    path: HOME,
    component: DashboardComponent,
    canActivate: [
      IsLoggedGuard,
    ],
  },

  {
    path: BETS,
    component: BetsCardComponent,
    canActivate: [
      IsLoggedGuard,
    ],
  },

  {
    path: RESULTS,
    component: ResultsMatchComponent,
    canActivate: [
      IsLoggedGuard,
    ],
  },

  {
    path: RANKS,
    component: RanksComponent,
    canActivate: [
      IsLoggedGuard,
    ],
  },

  {
    path: PASSWORD,
    component: PasswordFormComponent,
    canActivate: [
      IsLoggedGuard,
    ],
  },

  {
    path: ADMIN,
    component: AdminComponent,
    canActivate: [
      IsLoggedGuard,
    ],
  },

  {
    path: '',
    redirectTo: `/${LOGIN}`,
    pathMatch: 'full',
  },
];
