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
import {
  CargoOverviewDisplayDetailsComponent
} from "./cargo-overview/cargo-overview.display.details/cargo-overview.display.details.component";
import {BookingRouteComponent} from "./booking-route/booking-route.component";
import {BookingRequestComponent} from "./booking.request/booking.request.component";
import {AuthGuard} from "./guards/auth.guard";


const routes: Routes = [
  {path: 'register', component: RegistrationPageComponent},
  {path: 'login', component: LoginPageComponent},
  {

    path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard],
    data: {
      expectedRole: ['[ROLE_SHIPMENT_COMPANY]', '[ROLE_GOODS_COMPANY]']
    },
    children: [
      {
        path: 'transports', component: TransportsComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: '[ROLE_SHIPMENT_COMPANY]'
        },
      },
      {
        path: 'booking-requests', component: BookingRequestComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: '[ROLE_SHIPMENT_COMPANY]'
        },
      },
      {
        path: 'route/details', component: RouteDisplayDetailsComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]', '[ROLE_GOODS_COMPANY]']
        }
      },
      {
        path: 'cargo/details', component: CargoOverviewDisplayDetailsComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]', '[ROLE_GOODS_COMPANY]']
        }
      },
      {
        path: 'cargo/details/:id', component: CargoOverviewDisplayDetailsComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]', '[ROLE_GOODS_COMPANY]']
        }
      },
      {
        path: 'route/add', component: RouteAddComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: '[ROLE_SHIPMENT_COMPANY]'
        },
      },
      {
        path: 'route/edit/:id', component: RouteEditComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: '[ROLE_SHIPMENT_COMPANY]'
        },
      },
      {
        path: 'add', component: RouteAddComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]']
        },
      },
      {
        path: 'cargoType', component: CargoTypeComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]']
        },
      },
      {
        path: 'route', component: RouteComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]', '[ROLE_GOODS_COMPANY]']
        },
      },
      {
        path: 'cargo', component: CargoOverviewComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_SHIPMENT_COMPANY]', '[ROLE_GOODS_COMPANY]']
        },
      },
      {
        path: 'book', component: BookingRouteComponent, canActivate: [AuthGuard],
        data: {
          expectedRole: ['[ROLE_GOODS_COMPANY]']
        },
      }
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
