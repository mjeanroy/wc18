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

import {Component, DoCheck, Input, OnChanges, OnInit} from '@angular/core';
import { Bet } from '../../models';
import { BetsApiService } from '../../api';
import { SnackbarService } from '../../services';

@Component({
  selector: 'app-bet',
  templateUrl: './bet.component.html',
  styleUrls: [
    './bet.component.scss',
  ],
})
export class BetComponent implements OnInit, DoCheck {
  @Input("bet") bet: Bet;

  private _snackbarService: SnackbarService;
  private _betsApiService: BetsApiService;

  saving: boolean;
  btnColor: string;

  constructor(betsApiService: BetsApiService, snackbarService: SnackbarService) {
    this._betsApiService = betsApiService;
    this._snackbarService = snackbarService;
  }

  ngOnInit() {
    this.saving = false;
    this._updateBtnColor();
  }

  ngDoCheck() {
    this._updateBtnColor();
  }

  save() {
    this.saving = true;
    this._betsApiService.saveOrUpdate(this.bet).subscribe(
      (bet) => this._onSaved(bet),
      () => this._onSavedError(),
    );
  }

  private _updateBtnColor() {
    if (this.bet) {
      this.btnColor = this.bet.id ? 'success' : 'primary';
    }
  }

  private _onSaved(bet: Bet) {
    // Update current bet, important to get the id.
    this.bet = Object.assign(this.bet, bet);

    this._updateBtnColor();
    this._snackbarService.openTop('Enregistrement effectué !', 1000);
    this._onSavedDone();
  }

  private _onSavedError() {
    this._snackbarService.openTop('Une erreur s\'est produite...');
    this._onSavedDone();
  }

  private _onSavedDone() {
    this.saving = false;
  }
}
