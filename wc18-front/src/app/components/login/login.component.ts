/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Logger } from '../../log';
import { LoginService } from '../../services/login.service';
import { SnackbarService } from '../../services/snackbar.service';

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
    this._router.navigate(['home']);
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
