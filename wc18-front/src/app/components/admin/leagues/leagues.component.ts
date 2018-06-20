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
import { League } from '../../../models/league.model';
import { LeaguesApiService } from '../../../api';
import { User } from '../../../models';
import { MatDialog } from '@angular/material';
import { LeagueFormComponent } from './league-form.component';
import { LeagueUserFormComponent } from './league-user-form.component';

@Component({
  selector: 'app-leagues',
  templateUrl: './leagues.component.html',
})
export class LeaguesComponent implements OnInit {
  private _dialog: MatDialog;
  private _leaguesApiService: LeaguesApiService;

  leagues: League[];

  constructor(dialog: MatDialog, leaguesApiService: LeaguesApiService) {
    this._dialog = dialog;
    this._leaguesApiService = leaguesApiService;
  }

  ngOnInit() {
    this._leaguesApiService.findAll().subscribe((leagues) =>
      this.leagues = leagues
    );
  }

  openNewLeagueDialog() {
    const dialogRef = this._dialog.open(LeagueFormComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe((league: League) => {
      if (league) {
        this._createLeague(league);
      }
    });
  }

  openAddUserDialog(league: League) {
    const dialogRef = this._dialog.open(LeagueUserFormComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe((user: User) => {
      if (user) {
        this._addUser(league, user);
      }
    });
  }

  private _createLeague(league: League) {
    this._leaguesApiService.create(league).subscribe((result) => (
      this.leagues.push(result)
    ));
  }

  private _addUser(league: League, user: User) {
    this._leaguesApiService.addUser(league, user).subscribe(() => (
      league.users.push(user)
    ));
  }
}
