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
import { Match, Score } from '../../../models';
import { MatDialogRef } from '@angular/material';
import { MatchesApiService } from '../../../api';

@Component({
  selector: 'app-user-bet',
  templateUrl: './user-bet.component.html',
})
export class UserBetComponent implements OnInit {
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
