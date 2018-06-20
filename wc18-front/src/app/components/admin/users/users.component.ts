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
import { MatDialog } from '@angular/material';
import { Bet, Login, Match, Score, User } from '../../../models';
import { BetsApiService, UsersApiService } from '../../../api';
import { UserFormComponent } from './user-form.component';
import { UserBetComponent } from './user-bet.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
})
export class UsersComponent implements OnInit {
  private _dialog: MatDialog;
  private _usersApiService: UsersApiService;
  private _betsApiService: BetsApiService;

  users: User[];

  constructor(dialog: MatDialog, usersApiService: UsersApiService, betsApiService: BetsApiService) {
    this._dialog = dialog;
    this._usersApiService = usersApiService;
    this._betsApiService = betsApiService;
  }

  ngOnInit() {
    this._usersApiService.findAll().subscribe((users) => {
      this.users = users;
    });
  }

  openNewBetDialog(user) {
    const dialogReg = this._dialog.open(UserBetComponent, {
      width: '600px',
    });

    dialogReg.afterClosed().subscribe((result) => {
      if (result) {
        this._saveBet(user, result.match, result.score);
      }
    });
  }

  openNewUserDialog() {
    const dialogRef = this._dialog.open(UserFormComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe((user: Login) => {
      if (user) {
        this._createUser(user);
      }
    });
  }

  private _saveBet(user: User, match: Match, score: Score) {
    const bet: Bet = {
      id: null,
      date: new Date(),
      result: 'UNAVAILABLE',
      point: 0,

      user,
      match,
      score,
    };

    this._betsApiService.save(user, bet).subscribe();
  }

  private _createUser(account: Login) {
    this._usersApiService.create(account).subscribe((user) => (
      this.users.push(user)
    ));
  }
}
