import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationPageComponent } from './registration-page/registration-page.component'
import { LoginPageComponent } from './login-page/login-page.component'
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  {path: 'register', component: RegistrationPageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'dashboard', component: DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
