import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AvailableDaysRentDto} from "../model/availableDaysRentDto";
import {RouteDto} from "../model/route.dto";
import {RouteCommand} from "./RouteCommand";

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http: HttpClient) {
  }

  createRoute(routeCommand: RouteCommand){
    return this.http.post<RouteCommand>("http://localhost:8080/api/route", routeCommand)
  }

  getRoute() {
    return this.http.get<any>("http://localhost:8080/api/route")
  }

  getRouteById(id: number) {
    return this.http.get<RouteDto>("http://localhost:8080/api/route/" + id)
  }

  putRoute(data: any, id: number) {
    return this.http.put<any>("http://localhost:8080/api/route/" + id, data)
  }

  deleteRoute(id: number) {
    return this.http.delete("http://localhost:8080/api/route/" + id);
  }

  getAvailableDaysRent() {
    return this.http.get<AvailableDaysRentDto[]>("http://localhost:8080/api/route/available-days");
  }
}
