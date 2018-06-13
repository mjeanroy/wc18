/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule, MatDialogModule, MatFormFieldModule, MatIconModule, MatInputModule } from '@angular/material';
import { AdminComponent } from './admin.component';
import { UsersComponent } from "./users/users.component";
import { UserFormComponent } from './users/user-form.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
  ],

  declarations: [
    AdminComponent,
    UsersComponent,
    UserFormComponent,
  ],

  entryComponents: [
    UserFormComponent,
  ],

  exports: [
    AdminComponent,
    UserFormComponent,
  ],
})
export class AdminModule {

}
