import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CargoTypeDto} from "../model/cargoType.dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CargoTypeService {

  constructor(private http: HttpClient) {
  }
  addCargoType(data : CargoTypeDto){
    return this.http.post<CargoTypeDto>("http://localhost:8080/api/cargoType",data)
  }
  getCargoType(): Observable<CargoTypeDto[]> {
    return this.http.get<CargoTypeDto[]>("http://localhost:8080/api/cargoType")
  }
  putCargoType(data : CargoTypeDto, id : number){
    return this.http.put<any>("http://localhost:8080/api/cargoType/" + id,data)
  }

  deleteCargoType(id : number){
    return this.http.delete<any>("http://localhost:8080/api/cargoType/"+id);
  }
}
