/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component } from '@angular/core';
import { Passwords } from '../../models';
import { MeApiService, UsersApiService } from '../../api';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'password-form',
  templateUrl: './password-form.component.html',
  styleUrls: [
    './password-form.component.scss',
  ],
})
export class PasswordFormComponent {

  private _meApiService: MeApiService;

  saving: boolean;
  form: Passwords;

  constructor(meApiService: MeApiService) {
    this._meApiService = meApiService;
    this.saving = false;
    this.form = {
      oldPassword: '',
      newPassword: '',
    };
  }

  validate(form: FormControl) {
    this.saving = true;

    this._meApiService.updatePassword(this.form).subscribe(() => {
      this.saving = false;

      form.reset();
      this.form.oldPassword = '';
      this.form.newPassword = '';
    });
  }
}
