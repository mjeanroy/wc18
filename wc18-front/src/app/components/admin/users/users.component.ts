/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { User } from '../../../models';
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
    let dialogRef = this._dialog.open(UserFormComponent, {
      height: '400px',
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
