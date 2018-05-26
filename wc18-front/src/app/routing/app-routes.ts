/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { BetsCardComponent } from '../components/bets/bets-card.component';

export const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },

  {
    path: 'home',
    component: DashboardComponent,
  },

  {
    path: 'bets',
    component: BetsCardComponent,
  },

  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
];
