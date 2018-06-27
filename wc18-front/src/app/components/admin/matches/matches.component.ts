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
import { MatchesApiService } from '../../../api';
import { Match, Score } from '../../../models';
import { MatchEditScoreFormComponent } from './match-edit-score-form.component';
import { MatDialog } from '@angular/material';
import { MatchEditFormComponent } from './match-edit-form.component';

@Component({
  selector: 'app-matches',
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

  openNewMatchDialog() {
    const dialogRef = this._dialog.open(MatchEditFormComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe((match) => {
      this._matchesApiService.create(match).subscribe((result: Match) =>
        this.matches.push(result)
      );
    });
  }

  editMatchScore(match: Match) {
    const dialogRef = this._dialog.open(MatchEditScoreFormComponent, {
      width: '600px',
      data: match.score,
    });

    dialogRef.afterClosed().subscribe((score: Score) => {
      if (score) {
        this._matchesApiService.updateScore(match, score).subscribe((result: Match) =>
          Object.assign(match, result)
        );
      }
    });
  }

  editMatch(match: Match) {
    const dialogRef = this._dialog.open(MatchEditFormComponent, {
      width: '600px',
      data: match,
    });

    dialogRef.afterClosed().subscribe((match: Match) => {
      debugger;
      if (match) {
        this._matchesApiService.update(match).subscribe((result: Match) =>
          Object.assign(match, result)
        );
      }
    });
  }
}
