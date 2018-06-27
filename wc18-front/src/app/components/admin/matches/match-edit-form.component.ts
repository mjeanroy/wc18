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

import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Match, Stage, Team } from '../../../models';
import { StagesApiService } from '../../../api/stages.api.service';
import { TeamsApiService } from '../../../api';
import { isNil } from '../../../common/is-nil';
import { dateIsoToUs } from '../../../common/date-iso-to-us';
import { dateUsToIso } from '../../../common/date-us-to-iso';

@Component({
  selector: 'app-match-edit-form',
  templateUrl: './match-edit-form.component.html',
})
export class MatchEditFormComponent implements OnInit {
  private _dialogRef: MatDialogRef<MatchEditFormComponent>;
  private _stagesApiService: StagesApiService;
  private _teamsApiService: TeamsApiService;

  teams: Team[];
  stages: Stage[];
  match: Match;
  selectedDate: string;

  constructor(
      teamsApiService: TeamsApiService,
      stagesApiService: StagesApiService,
      dialogRef: MatDialogRef<MatchEditFormComponent>,
      @Optional() @Inject(MAT_DIALOG_DATA) match: Match) {

    this._teamsApiService = teamsApiService;
    this._stagesApiService = stagesApiService;
    this._dialogRef = dialogRef;

    this.selectedDate = match ? dateIsoToUs(match.date) : null;
    this.match = Object.assign({}, match || {
      id: null,
      date: null,
      stage: null,
      team1: null,
      team2: null,
      score: null,
    });
  }

  ngOnInit() {
    this._teamsApiService.findAll().subscribe((teams) =>
      this.teams = teams
    );

    this._stagesApiService.findAll().subscribe((stages) =>
      this.stages = stages
    );
  }

  compareIds(x: any, y: any) {
    if (x === y) {
      return true;
    }

    if (isNil(x) || isNil(y)) {
      return false;
    }

    return x.id === y.id;
  }

  cancel() {
    this._dialogRef.close();
  }

  validate() {
    this._dialogRef.close(Object.assign(this.match, {
      date: dateUsToIso(this.selectedDate),
    }));
  }
}
