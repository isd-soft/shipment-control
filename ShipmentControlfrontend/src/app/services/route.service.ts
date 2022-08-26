import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TransportTypeDto} from "../model/transportTypeDto";
import {AvailableDaysRentDto} from "../model/availableDaysRentDto";

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http : HttpClient) { }

  createRoute(data : any){
    return this.http.post<any>("http://localhost:3000/routeList/",data)
  }
  getRoute(){
    return this.http.get<any>("http://localhost:8080/api/route/")
  }
  putRoute(data : any, id : number){
    return this.http.put<any>("http://localhost:3000/routeList/" + id,data)
  }

  deleteRoute(id : number){
    return this.http.delete<any>("http://localhost:3000/routeList/"+id);
  }
  getAvailableDaysRent() {
    return this.http.get<AvailableDaysRentDto[]>("http://localhost:8080/api/route/available-days");
  }
}
