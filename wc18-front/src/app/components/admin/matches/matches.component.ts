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
