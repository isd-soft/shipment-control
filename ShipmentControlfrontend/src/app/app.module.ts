import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';

import { Input, Component, Output, EventEmitter, OnInit } from '@angular/core';
import {FormGroup, FormControl, FormsModule} from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule} from "@angular/material/checkbox";
import { MatSelectModule} from "@angular/material/select";
import { ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from "@angular/material/icon";


import { MatPasswordStrengthModule } from '@angular-material-extensions/password-strength';
import {HttpClientModule} from "@angular/common/http";
import {DashboardComponent } from './dashboard/dashboard.component';
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatMenuModule} from "@angular/material/menu";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MenuItemComponent} from "./dashboard/menu-item/menu-item.component";
import { LoginRegisterNavBarComponent } from './login-register-nav-bar/login-register-nav-bar.component';
import { TransportsComponent } from './transports/transports.component';
import {MatTableModule} from "@angular/material/table";


// import RouteIcon from '@mui/icons-material/Route';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegistrationPageComponent,
    DashboardComponent,
    MenuItemComponent,
    LoginRegisterNavBarComponent,
    TransportsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatCheckboxModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatPasswordStrengthModule,
    BrowserModule,
    HttpClientModule,
    MatListModule,
    MatExpansionModule,
    MatMenuModule,
    MatSidenavModule,
    MatTableModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


