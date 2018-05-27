/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material';
import { RouterModule } from '@angular/router';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { ServicesModule } from '../../services';
import { NavBarComponent } from './navbar.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([]),
    MatIconModule,
    NgbDropdownModule,
    ServicesModule,
  ],

  declarations: [
    NavBarComponent,
  ],

  exports: [
    NavBarComponent,
  ],
})
export class NavbarModule {
}
