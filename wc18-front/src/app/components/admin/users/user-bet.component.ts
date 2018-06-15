/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { Match, Score } from '../../../models';
import { MatDialogRef } from '@angular/material';
import { MatchesApiService } from '../../../api';

@Component({
  selector: 'user-bet',
  templateUrl: './user-bet.component.html',
})
export class UserBetComponent implements OnInit{
  private _dialogRef: MatDialogRef<UserBetComponent>;
  private _matchesApiService: MatchesApiService;

  matches: Match[];

  match: Match;
  score: Score;

  constructor(dialogRef: MatDialogRef<UserBetComponent>, matchesApiService: MatchesApiService) {
    this._dialogRef = dialogRef;
    this._matchesApiService = matchesApiService;

    this.match = null;
    this.score = {
      score1: 0,
      score2: 0,
    };
  }

  ngOnInit() {
    this._matchesApiService.findLocked().subscribe((matches) =>
      this.matches = matches
    );
  }

  onChange() {
    console.log('on change: ', this.match);
  }

  cancel(): void {
    this._dialogRef.close();
  }

  validate() {
    this._dialogRef.close({
      match: this.match,
      score: this.score,
    });
  }
}
