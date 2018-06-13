import {Component} from "@angular/core";
import {Login} from "../../../models";

@Component({
  selector: 'user-form',
  templateUrl: './user-form.component.html',
  styleUrls: [
    './user-form.component.scss',
  ],
})
export class UserFormComponent {
  user: Login;

  constructor() {
    this.user = {
      login: '',
      password: '',
    };
  }

  addUser() {
    console.log('add');
  }
}
