/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: [
    './navbar.component.css',
  ]
})
export class NavBarComponent {
  private readonly _loginService: LoginService;
  private readonly _router: Router;

  constructor(loginService: LoginService, router: Router) {
    this._loginService = loginService;
    this._router = router;
  }

  isLogged() {
    return this._loginService.isLogged();
  }

  logout() {
    this._loginService.logout()
      .subscribe(
        () => this._onLoggedOut(),
      );
  }

  private _onLoggedOut() {
    this._router.navigate(['login']);
  }
}
