/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../auth';
import { Logger } from '../log';
import { LOGIN } from '../routing/paths';

@Injectable({
  providedIn: 'root',
})
export class IsLoggedGuard implements CanActivate {

  private _logger: Logger;
  private _authService: AuthService;
  private _router: Router;

  constructor(logger: Logger, authService: AuthService, router: Router) {
    this._logger = logger;
    this._authService = authService;
    this._router = router;
  }

  /**
   * Ensure that route can be activated by checking if user is authenticated, otherwise
   * redirect to the login view.
   *
   * @returns {boolean} `true` if user is authenticated and route can be activated, `false` otherwise.
   * @override
   */
  canActivate(): boolean {
    const isLogged = this._authService.isLogged();
    if (!isLogged) {
      this._logger.debug('Routes needs authentication but user is not logged, redirect to LOGIN');
      this._router.navigate([LOGIN]);
    }

    return isLogged;
  }
}
