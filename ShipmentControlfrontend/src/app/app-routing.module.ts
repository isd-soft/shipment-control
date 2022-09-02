import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationPageComponent} from './registration-page/registration-page.component'
import {LoginPageComponent} from './login-page/login-page.component'
import {DashboardComponent} from "./dashboard/dashboard.component";
import {TransportsComponent} from "./transports/transports.component";
import {CargoTypeComponent} from "./cargoType/cargoType.component";
import {CargoOverviewComponent} from "./cargo-overview/cargo-overview.component";
import {RouteComponent} from "./route/route.component";
import {RouteAddComponent} from "./route/route.add/route.add.component";
import {RouteDisplayDetailsComponent} from "./route/route.display.details/route.display.details.component";
import {RouteEditComponent} from "./route/route.edit/route.edit.component";
import {BookingRequestComponent} from "./booking.request/booking.request.component";


const routes: Routes = [
  {path: 'register', component: RegistrationPageComponent},
  {path: 'login', component: LoginPageComponent},
  {

    path: 'dashboard', component: DashboardComponent,
    children: [
      {path: 'transports', component: TransportsComponent},
      {path: 'booking-requests', component: BookingRequestComponent},
      {path: 'route/details', component: RouteDisplayDetailsComponent},
      {path: 'route/add', component: RouteAddComponent},
      {path: 'route/edit/:id', component: RouteEditComponent},
      {path: 'add', component: RouteAddComponent},
      {path: 'cargoType', component: CargoTypeComponent},
      {path: 'route', component: RouteComponent},
      {path: 'cargo', component: CargoOverviewComponent}
    ]

  },
  {path: '', component: LoginPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
