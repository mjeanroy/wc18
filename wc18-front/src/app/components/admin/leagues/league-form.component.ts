/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import {Component} from "@angular/core";
import { MatDialogRef } from '@angular/material';
import { League } from '../../../models/league.model';

@Component({
  selector: 'league-form',
  templateUrl: './league-form.component.html',
})
export class LeagueFormComponent {
  private _dialogRef: MatDialogRef<LeagueFormComponent>;

  league: League;

  constructor(dialogRef: MatDialogRef<LeagueFormComponent>) {
    this._dialogRef = dialogRef;

    this.league = {
      id: null,
      name: '',
      users: [],
    };
  }

  cancel() {
    this._dialogRef.close();
  }

  validate() {
    this._dialogRef.close(this.league);
  }
}
