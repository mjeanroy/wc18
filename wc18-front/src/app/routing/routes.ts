/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Routes } from '@angular/router';
import { LoginComponent } from '../components/login';
import { DashboardComponent } from '../components/dashboard';
import { BetsCardComponent } from '../components/bets';
import { IsLoggedGuard, IsNotLoggedGuard } from '../guards';
import { ADMIN, BETS, HOME, LOGIN, PASSWORD } from './paths';
import { AdminComponent } from "../components/admin/admin.component";
import { PasswordFormComponent } from '../components/password/password-form.component';

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
