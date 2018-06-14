/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { League } from '../../../models/league.model';
import { LeaguesApiService } from '../../../api';
import { User } from '../../../models';
import { MatDialog } from '@angular/material';
import { LeagueFormComponent } from './league-form.component';
import { LeagueUserFormComponent } from './league-user-form.component';

@Component({
  selector: 'leagues',
  templateUrl: './leagues.component.html',
})
export class LeaguesComponent implements OnInit{
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
        this._createLeague(league)
      }
    });
  }

  openAddUserDialog(league: League) {
    const dialogRef = this._dialog.open(LeagueUserFormComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe((user: User) => {
      if (user) {
        this._addUser(league, user)
      }
    });
  }

  private _createLeague(league: League) {
    this._leaguesApiService.create(league).subscribe((result) => (
      this.leagues.push(result)
    ));
  }

  private _addUser(league: League, user: User) {
    this._leaguesApiService.addUser(league, user).subscribe(() => {
      league.users.push(user)
    });
  }
}
