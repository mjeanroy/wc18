/**
 * Copyright (C) Mickael Jeanroy - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatToolbarModule } from '@angular/material';
import { ServicesModule } from '../../services/services.module';
import { NavBarComponent } from './navbar.component';

@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
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
