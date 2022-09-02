import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CargoDTO} from "../model/cargoOverview.dto";
import {CargoOverviewCommand} from "./CargoOverviewCommand";
import {RouteDto} from "../model/route.dto";

@Injectable({
  providedIn: 'root'
})
export class CargoOverviewService {

  url = "http://localhost:8080/api/cargoOverview";
  constructor(private http: HttpClient) {
  }

  getCargoById(id: number) {
    return this.http.get<CargoDTO>( `${this.url}/${id}` );
  }

  addCargoOverview(data: CargoOverviewCommand){
    return this.http.post<CargoOverviewCommand>("http://localhost:8080/api/cargoOverview",data)
  }
  getCargoOverview(): Observable<CargoDTO[]> {
    return this.http.get<CargoDTO[]>("http://localhost:8080/api/cargoOverview")
  }
  putCargoOverview(data : CargoOverviewCommand, id : number){
    return this.http.put<any>("http://localhost:8080/api/cargoOverview/" + id,data)
  }

  deleteCargoOverview(id : number){
    return this.http.delete<any>("http://localhost:8080/api/cargoOverview/"+id);
  }
}
