/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component } from '@angular/core';
import { LoginService } from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [
    './login.component.css',
  ]
})
export class LoginComponent {
  private _loginService: LoginService;

  login: string;
  password: string;

  constructor(loginService: LoginService) {
    this._loginService = loginService;

    this.login = '';
    this.password = '';
  }

  submit() {
    this._loginService.login(this.login, this.password)
      .subscribe(() => {
        console.log('Logged Successfully');
      });
  }
}
