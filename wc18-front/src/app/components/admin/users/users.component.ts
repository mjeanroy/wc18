/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Login, User } from '../../../models';
import { UsersApiService } from '../../../api';
import {MatDialog} from "@angular/material";
import {UserFormComponent} from "./user-form.component";

@Component({
  selector: 'users',
  templateUrl: './users.component.html',
})
export class UsersComponent implements OnInit {
  private _dialog: MatDialog;
  private _usersApiService: UsersApiService;

  users: User[];

  constructor(dialog: MatDialog, usersApiService: UsersApiService) {
    this._dialog = dialog;
    this._usersApiService = usersApiService;
  }

  ngOnInit() {
    this._usersApiService.findAll().subscribe((users) => {
      this.users = users;
    });
  }

  openNewUserDialog() {
    const dialogRef = this._dialog.open(UserFormComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe((user: Login) => (
      this._createUser(user)
    ));
  }

  private _createUser(account: Login) {
    this._usersApiService.create(account).subscribe((user) => (
      this.users.push(user)
    ));
  }
}
