/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import {Component, Input, OnInit} from '@angular/core';
import { Bet } from '../../models/bet.model';
import {BetsService} from "../../services/bets.service";
import {SnackbarService} from "../../services/snackbar.service";

@Component({
  selector: 'app-bet',
  templateUrl: './bet.component.html',
  styleUrls: [
    './bet.component.css',
  ]
})
export class BetComponent implements OnInit {
  @Input("bet") bet: Bet;

  private _snackbarService: SnackbarService;
  private _betsService: BetsService;

  saving: boolean;
  btnColor: string;

  constructor(betsService: BetsService, snackbarService: SnackbarService) {
    this._betsService = betsService;
    this._snackbarService = snackbarService;
  }

  ngOnInit() {
    this.saving = false;
    this._updateBtnColor();
  }

  save() {
    this.saving = true;
    this._betsService.saveOrUpdate(this.bet).subscribe(
      (bet) => this._onSaved(bet),
      () => this._onSavedError(),
    );
  }

  private _updateBtnColor() {
    this.btnColor = this.bet.id ? 'success' : 'primary';
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