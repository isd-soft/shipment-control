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
    return this.http.post<CargoTypeDto>("http://localhost:3000/cargoTypeList/",data)
  }
  getCargoType(): Observable<CargoTypeDto[]> {
    return this.http.get<CargoTypeDto[]>("http://localhost:3000/cargoTypeList/")
  }
  putCargoType(data : any, id : number){
    return this.http.put<any>("http://localhost:3000/cargoTypeList/" + id,data)
  }

  deleteCargoType(id : number){
    return this.http.delete<any>("http://localhost:3000/cargoTypeList/"+id);
  }
}
