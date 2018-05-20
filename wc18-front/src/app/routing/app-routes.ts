/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { MatchesComponent } from '../components/matches/matches.component';

export const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },

  {
    path: 'home',
    component: MatchesComponent,
  },

  {
    path: 'matches',
    component: MatchesComponent,
  },

  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
];
