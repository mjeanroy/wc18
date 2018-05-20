import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCardModule, MatFormFieldModule, MatInputModule, MatToolbarModule } from '@angular/material';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { NavBarComponent } from './components/navbar/navbar.component';

import { LoginService } from './services/login.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavBarComponent,
  ],

  imports: [
    // @angular
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,

    // @angular/material
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
  ],

  providers: [
    LoginService
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
