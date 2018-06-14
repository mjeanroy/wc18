import {Component} from "@angular/core";
import {Login} from "../../../models";
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'user-form',
  templateUrl: './user-form.component.html',
})
export class UserFormComponent {
  private _dialogRef: MatDialogRef<UserFormComponent>;

  user: Login;

  constructor(dialogRef: MatDialogRef<UserFormComponent>) {
    this._dialogRef = dialogRef;

    this.user = {
      login: '',
      password: '',
    };
  }

  cancel(): void {
    this._dialogRef.close();
  }

  validate() {
    this._dialogRef.close(this.user);
  }
}
