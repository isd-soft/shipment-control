import { Injectable } from '@angular/core';
import {BookingRouteCommand} from "./BookingRouteCommand";
import {RouteCommand} from "./RouteCommand";
import {HttpClient} from "@angular/common/http";
import {LegDto} from "../model/leg.dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class BookingService {
  constructor(private http: HttpClient) {
  }

  createBookingRoute(bookingRouteCommand:BookingRouteCommand) {
    return this.http.post("http://localhost:8080/api/cargo", bookingRouteCommand);
  }

  getAllLegsFromRoute(routeId: string): Observable<LegDto[]> {
    return this.http.get<LegDto[]>("http://localhost:8080/api/route/" + routeId + "/legs");
  }
}
