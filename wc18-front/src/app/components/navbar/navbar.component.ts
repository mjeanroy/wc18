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
   * Check if the authenticated user is an administrator.
   *
   * @returns {boolean} `true` if an authenticated user exists and is administrator, `false` otherwise.
   */
  isAdmin() {
    return this._loginService.isAdmin();
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
