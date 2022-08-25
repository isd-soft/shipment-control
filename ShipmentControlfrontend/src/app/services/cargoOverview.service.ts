import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CargoDTO} from "../model/cargoOverview.dto";

@Injectable({
  providedIn: 'root'
})
export class CargoOverviewService {

  constructor(private http: HttpClient) {
  }

  addCargoOverview(data: { trackingNumber: any }){
    return this.http.post<CargoDTO>("http://localhost:8080/api/cargoOverview",data)
  }
  getCargoOverview(): Observable<CargoDTO[]> {
    return this.http.get<CargoDTO[]>("http://localhost:8080/api/cargoOverview")
  }
  putCargoOverview(data : CargoDTO, id : number){
    return this.http.put<any>("http://localhost:8080/api/cargoOverview/" + id,data)
  }

  deleteCargoOverview(id : number){
    return this.http.delete<any>("http://localhost:8080/api/cargoOverview/"+id);
  }
}
