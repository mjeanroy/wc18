/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login';
import { DashboardComponent } from '../components/dashboard';
import { BetsCardComponent } from '../components/bets';
import { BETS, HOME, LOGIN } from './paths';

export const routes: Routes = [
  {
    path: LOGIN,
    component: LoginComponent,
  },

  {
    path: HOME,
    component: DashboardComponent,
  },

  {
    path: BETS,
    component: BetsCardComponent,
  },

  {
    path: '',
    redirectTo: `/${LOGIN}`,
    pathMatch: 'full',
  },
];
