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
