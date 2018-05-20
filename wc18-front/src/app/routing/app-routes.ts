/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { BetsComponent } from '../components/bets/bets.component';

export const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },

  {
    path: 'home',
    component: BetsComponent,
  },

  {
    path: 'bets',
    component: BetsComponent,
  },

  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
];
