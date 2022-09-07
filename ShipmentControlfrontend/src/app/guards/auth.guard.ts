import {Injectable} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,

} from '@angular/router';
import {AuthService} from "../services/auth.service";
import decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const token = localStorage.getItem('token');
    const expectedRole = route.data['expectedRole'] as Array<string>;
    // @ts-ignore;
    const tokenPayload = decode(token).sub;

    if (
      !this.authService.isAuthenticated() ||
      !expectedRole.includes(tokenPayload)
    ) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
