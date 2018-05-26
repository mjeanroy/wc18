import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { AuthInterceptor } from './auth.interceptor';
import { AuthService } from './auth.service';
import { AuthStorage } from './auth.storage';

@NgModule({
  imports: [
    CommonModule,
  ],

  providers: [
    AuthService,
    AuthStorage,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    }
  ],
})
export class AuthModule {
}
