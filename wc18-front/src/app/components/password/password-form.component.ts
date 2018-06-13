/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component } from '@angular/core';
import { Passwords } from '../../models';
import { UsersApiService } from '../../api';

@Component({
  selector: 'password-form',
  templateUrl: './password-form.component.html',
  styleUrls: [
    './password-form.component.scss',
  ],
})
export class PasswordFormComponent {

  private _userApiService: UsersApiService;

  saving: boolean;
  form: Passwords;

  constructor(userApiService: UsersApiService) {
    this._userApiService = userApiService;
    this.saving = false;
    this.form = {
      oldPassword: '',
      newPassword: '',
    };
  }

  validate() {
    this.saving = true;

    this._userApiService.updatePassword(this.form).subscribe(() => {
      this.saving = false;
    });
  }
}
