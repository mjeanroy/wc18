/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Bet, Login, Match, Score, User } from '../../../models';
import { BetsApiService, UsersApiService } from '../../../api';
import {MatDialog} from "@angular/material";
import {UserFormComponent} from "./user-form.component";
import { UserBetComponent } from './user-bet.component';

@Component({
  selector: 'users',
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
