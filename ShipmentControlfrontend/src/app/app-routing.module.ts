import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationPageComponent} from './registration-page/registration-page.component'
import {LoginPageComponent} from './login-page/login-page.component'
import {DashboardComponent} from "./dashboard/dashboard.component";
import {TransportsComponent} from "./transports/transports.component";
import {CargoTypeComponent} from "./cargoType/cargoType.component";
import {CargoOverviewComponent} from "./cargo-overview/cargo-overview.component";


const routes: Routes = [
  {path: 'register', component: RegistrationPageComponent},
  {path: 'login', component: LoginPageComponent},
  {
    path: 'dashboard', component: DashboardComponent,
    children: [
      {path: 'transports', component: TransportsComponent},
      {path: 'cargoType', component: CargoTypeComponent},
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
