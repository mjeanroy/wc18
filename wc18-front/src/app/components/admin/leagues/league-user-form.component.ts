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
import { User } from '../../../models';
import { MatDialogRef } from '@angular/material';
import { UsersApiService } from '../../../api';

@Component({
  selector: 'app-league-user-form',
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
