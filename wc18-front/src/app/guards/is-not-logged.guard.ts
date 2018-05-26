/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../auth';
import { Logger } from '../log';
import { HOME } from '../routing/paths';

@Injectable({
  providedIn: 'root',
})
export class IsNotLoggedGuard implements CanActivate {

  private _logger: Logger;
  private _authService: AuthService;
  private _router: Router;

  constructor(logger: Logger, authService: AuthService, router: Router) {
    this._logger = logger;
    this._authService = authService;
    this._router = router;
  }

  /**
   * Ensure that route can be activated by checking if user is not authenticated, otherwise
   * redirect to the home view.
   *
   * @returns {boolean} `true` if user is not authenticated and route can be activated, `false` otherwise.
   * @override
   */
  canActivate(): boolean {
    const isNotLogged = !this._authService.isLogged();
    if (!isNotLogged) {
      this._logger.debug('User is already authenticated, redirect to HOME');
      this._router.navigate([HOME]);
    }

    return isNotLogged;
  }
}
