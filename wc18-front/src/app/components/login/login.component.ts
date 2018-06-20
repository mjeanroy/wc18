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

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Logger } from '../../log';
import { HOME } from '../../routing/paths';
import { LoginService, SnackbarService } from '../../services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [
    './login.component.css',
  ]
})
export class LoginComponent implements OnInit {

  private readonly _logger: Logger;
  private readonly _loginService: LoginService;
  private readonly _snackbarService: SnackbarService;
  private readonly _router: Router;

  login: string;
  password: string;
  loading: boolean;

  constructor(logger: Logger, loginService: LoginService, snackbarService: SnackbarService, router: Router) {
    this._logger = logger;
    this._loginService = loginService;
    this._snackbarService = snackbarService;
    this._router = router;
  }

  /**
   * Initialize component.
   *
   * @returns {void}
   * @override
   */
  ngOnInit() {
    this.login = '';
    this.password = '';
    this.loading = false;
  }

  /**
   * Submit login form.
   *
   * @returns {void}
   */
  submit() {
    if (this.loading) {
      this._logger.debug('Login already in progress, skip attempt');
      return;
    }

    this._logger.debug('Process login authentication');

    this.loading = true;
    this._loginService.login(this.login, this.password)
      .subscribe(
        () => this._onLogged(),
        () => this._onLoggedError(),
      );
  }

  private _onLogged() {
    this._logger.debug('Login succeed, redirect to HOME');
    this._router.navigate([HOME]);
    this._onLoggedDone();
  }

  private _onLoggedError() {
    this._logger.debug('Login failed');
    this._snackbarService.openTop('Identifiants Invalide');
    this._onLoggedDone();
  }

  private _onLoggedDone() {
    this.loading = false;
  }
}
