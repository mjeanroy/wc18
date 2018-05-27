/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: [
    './navbar.component.scss',
  ],
})
export class NavBarComponent implements OnInit {
  private readonly _loginService: LoginService;
  private readonly _router: Router;

  collapsed: boolean;

  constructor(loginService: LoginService, router: Router) {
    this._loginService = loginService;
    this._router = router;
  }

  ngOnInit() {
    this.collapsed = true;
  }

  /**
   * Collapse navbar.
   *
   * @returns {void}
   */
  collapse() {
    this.collapsed = !this.collapsed;
  }

  /**
   * Check if there is an authenticated user.
   *
   * @returns {boolean} `true` if an authenticated user exists, `false` otherwise.
   */
  isLogged() {
    return this._loginService.isLogged();
  }

  /**
   * Trigger logout.
   *
   * @returns {void}
   */
  logout() {
    this._loginService.logout().subscribe(
      () => this._onLoggedOut(),
    );
  }

  private _onLoggedOut() {
    this._router.navigate(['login']);
  }
}
