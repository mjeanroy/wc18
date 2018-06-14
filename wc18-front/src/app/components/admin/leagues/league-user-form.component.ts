/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { User } from '../../../models';
import { MatDialogRef } from '@angular/material';
import { UsersApiService } from '../../../api';

@Component({
  selector: 'league-user-form',
  templateUrl: './league-user-form.component.html',
})
export class LeagueUserFormComponent implements OnInit {
  private _dialogRef: MatDialogRef<LeagueUserFormComponent>;
  private _usersApiService: UsersApiService;

  users: User[];
  user: User;

  constructor(usersApiService: UsersApiService, dialogRef: MatDialogRef<LeagueUserFormComponent>) {
    this._usersApiService = usersApiService;
    this._dialogRef = dialogRef;
  }

  ngOnInit() {
    this._usersApiService.findAll().subscribe((users) =>
      this.users = users
    );
  }

  cancel() {
    this._dialogRef.close();
  }

  validate() {
    this._dialogRef.close(this.user);
  }
}
