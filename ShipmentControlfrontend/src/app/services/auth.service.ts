import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoginCommand} from "./LoginCommand";
import {TokenResponse} from "./TokenResponse";

const AUTH_API = 'http://localhost:8080/api/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(loginCommand: LoginCommand): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(
      AUTH_API + 'signin',
      loginCommand,
      httpOptions
    );
  };

}
