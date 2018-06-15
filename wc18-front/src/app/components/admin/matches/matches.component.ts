/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, OnInit } from '@angular/core';
import { MatchesApiService } from '../../../api';
import { Match, Score } from '../../../models';
import { MatchEditFormComponent } from './match-edit-form.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'matches',
  templateUrl: './matches.component.html',
})
export class MatchesComponent implements OnInit {
  private _dialog: MatDialog;
  private _matchesApiService: MatchesApiService;

  matches: Match[];

  constructor(dialog: MatDialog, matchesApiService: MatchesApiService) {
    this._dialog = dialog;
    this._matchesApiService = matchesApiService;
  }

  ngOnInit() {

    this._matchesApiService.findAll().subscribe((matches) =>
      this.matches = matches
    );
  }

  editMatch(match: Match) {
    const dialogRef = this._dialog.open(MatchEditFormComponent, {
      width: '600px',
      data: match.score,
    });

    dialogRef.afterClosed().subscribe((score: Score) => {
      if (score) {
        const input = Object.assign({}, match, {
          score,
        });

        this._matchesApiService.update(input).subscribe((result: Match) =>
          Object.assign(match, result)
        );
      }
    });
  }
}
