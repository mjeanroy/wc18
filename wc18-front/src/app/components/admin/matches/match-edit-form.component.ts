/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Component, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Score } from '../../../models';

@Component({
  selector: 'match-edit-form',
  templateUrl: './match-edit-form.component.html',
})
export class MatchEditFormComponent {
  private _dialogRef: MatDialogRef<MatchEditFormComponent>;

  score: Score;

  constructor(dialogRef: MatDialogRef<MatchEditFormComponent>, @Optional() @Inject(MAT_DIALOG_DATA) score: Score) {
    this._dialogRef = dialogRef;
    this.score = score || {
      score1: 0,
      score2: 0,
    };
  }

  cancel() {
    this._dialogRef.close();
  }

  validate() {
    this._dialogRef.close(this.score);
  }
}
