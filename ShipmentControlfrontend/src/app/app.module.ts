import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {NgxMatTimepickerModule} from 'ngx-mat-timepicker';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {TokenInterceptor} from './services/token.interceptor';
import {FormGroup, FormControl, FormsModule} from '@angular/forms';

import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatSelectModule} from "@angular/material/select";
import {ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";


import {MatPasswordStrengthModule} from '@angular-material-extensions/password-strength';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {DashboardComponent} from './dashboard/dashboard.component';
import {MatListModule} from "@angular/material/list";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatMenuModule} from "@angular/material/menu";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MenuItemComponent} from "./dashboard/menu-item/menu-item.component";
import {LoginRegisterNavBarComponent} from './login-register-nav-bar/login-register-nav-bar.component';
import {TransportsComponent} from './transports/transports.component';

import {MatTableModule} from "@angular/material/table";
import {CargoTypeComponent} from './cargoType/cargoType.component';
import {DialogCargoTypeComponent} from './cargoType/dialog/dialogCargoType.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSnackBarModule} from '@angular/material/snack-bar';

import {MatSortModule} from '@angular/material/sort';

import {TransportsDialogComponent} from './transports/transports.dialog/transports.dialog.component';

import {CustomMaterialModule} from './cargoType/custom-material.module';
import {ConfirmDialogComponent} from './cargoType/dialog/confirm-dialog.component';


import {RouteComponent} from './route/route.component';
import {RouteAddComponent} from './route/route.add/route.add.component';
import {RouteDisplayDetailsComponent} from './route/route.display.details/route.display.details.component';
import {MatTabsModule} from "@angular/material/tabs";
import {ConfirmDialogCargoComponent} from "./cargo-overview/dialog/confirm-dialogCargo.component";
import {DialogCargoOverviewComponent} from "./cargo-overview/dialog/dialogCargoOverview.component";
import {CargoOverviewComponent} from "./cargo-overview/cargo-overview.component";
import {RouteConfirmDialogComponent} from './route/route.confirm.dialog/route.confirm.dialog.component';
import {RouteEditComponent} from "./route/route.edit/route.edit.component";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {DatePipe} from '@angular/common';
import { CargoOverviewDisplayDetailsComponent } from './cargo-overview/cargo-overview.display.details/cargo-overview.display.details.component';
import {BookingRouteComponent} from "./booking-route/booking-route.component";
import {BookingRequestComponent} from "./booking.request/booking.request.component";
import { SharedModule } from './shared/shared.module';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegistrationPageComponent,
    DashboardComponent,
    MenuItemComponent,
    LoginRegisterNavBarComponent,
    TransportsComponent,
    CargoTypeComponent,
    DialogCargoTypeComponent,
    TransportsDialogComponent,
    ConfirmDialogComponent,
    RouteComponent,
    RouteAddComponent,
    RouteDisplayDetailsComponent,
    ConfirmDialogCargoComponent,
    DialogCargoOverviewComponent,
    CargoOverviewComponent,
    RouteConfirmDialogComponent,
    RouteEditComponent,
    CargoOverviewDisplayDetailsComponent,
    BookingRouteComponent,
    BookingRequestComponent

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
    HttpClientModule,
    MatSortModule,
    MatDialogModule,
    MatPaginatorModule,
    MatSnackBarModule,
    CustomMaterialModule,
    NgxMatTimepickerModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    DatePipe,
    SharedModule

  ],
  entryComponents: [ConfirmDialogComponent],
  exports: [
    MatSortModule,
    MatPaginatorModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    DatePipe
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}

