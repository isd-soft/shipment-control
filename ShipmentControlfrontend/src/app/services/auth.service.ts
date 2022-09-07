import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoginCommand} from "./LoginCommand";
import {TokenResponse} from "./TokenResponse";
import {JwtHelperService, JWT_OPTIONS} from "@auth0/angular-jwt";

const AUTH_API = 'http://localhost:8080/api/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient,
              private jwtHelper:JwtHelperService) {}

  login(loginCommand: LoginCommand): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(
      AUTH_API + 'signin',
      loginCommand,
      httpOptions
    );
  }
  public isAuthenticated(): boolean {
    const token= localStorage.getItem('token');
    // @ts-ignore
    return !this.jwtHelper.isTokenExpired(token,3600);
  }


}
