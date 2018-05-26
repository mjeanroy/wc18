/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatSnackBarModule } from '@angular/material';
import { ApiModule } from '../api';
import { AuthModule } from '../auth/auth.module';
import { LogModule } from '../log';
import { LoginService } from './login.service';
import { SnackbarService } from './snackbar.service';

@NgModule({
  imports: [
    CommonModule,
    MatSnackBarModule,
    ApiModule,
    AuthModule,
    LogModule,
  ],

  providers: [
    LoginService,
    SnackbarService,
  ],
})
export class ServicesModule {
}
