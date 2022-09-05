import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CargoOverviewDTO} from "../model/cargoOverview.dto";
import {CargoOverviewCommand} from "./CargoOverviewCommand";

@Injectable({
  providedIn: 'root'
})
export class CargoOverviewService {

  url = "http://localhost:8080/api/cargoOverview";
  constructor(private http: HttpClient) {
  }

  getCargoOverviewById(id: number) {
    return this.http.get<CargoOverviewDTO>( `${this.url}/${id}` );
  }

  addCargoOverview(data: CargoOverviewCommand){
    return this.http.post<CargoOverviewCommand>("http://localhost:8080/api/cargoOverview",data)
  }


  getCargoOverview(): Observable<CargoOverviewDTO[]> {
    return this.http.get<CargoOverviewDTO[]>("http://localhost:8080/api/cargoOverview")
  }

  putCargoOverview(data : CargoOverviewCommand, id : number){
    return this.http.put<any>("http://localhost:8080/api/cargoOverview/" + id,data)
  }

  deleteCargoOverview(id : number){
    return this.http.delete<any>("http://localhost:8080/api/cargoOverview/"+id);
  }
}
