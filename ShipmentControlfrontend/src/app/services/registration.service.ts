import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { RegisterCommand } from "./RegisterCommand";


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient: HttpClient) {
  }

  register(registerCommand: RegisterCommand): Observable<void> {
    return this.httpClient.post<void>('http://localhost:8080/api/auth/signup', registerCommand);
  }
}
