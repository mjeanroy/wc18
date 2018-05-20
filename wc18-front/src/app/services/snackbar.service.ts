/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root',
})
export class SnackbarService {
  private readonly _snackbar: MatSnackBar;

  constructor(snackbar: MatSnackBar) {
    this._snackbar = snackbar;
  }

  openTop(message: string, duration: number = 5000) {
    this._snackbar.open(message, null, {
      duration,
      verticalPosition: 'top',
    });
  }
}
