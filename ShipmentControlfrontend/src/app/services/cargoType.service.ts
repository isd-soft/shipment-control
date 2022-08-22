import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CargoTypeService {

  constructor(private http: HttpClient) {
  }
  addCargoType(data : any){
    return this.http.post<any>("http://localhost:3000/cargoTypeList/",data)
  }
  getCargoType(){
    return this.http.get<any>("http://localhost:3000/cargoTypeList/")
  }
  putCargoType(data : any, id : number){
    return this.http.put<any>("http://localhost:3000/cargoTypeList/" + id,data)
  }

  deleteCargoType(id : number){
    return this.http.delete<any>("http://localhost:3000/cargoTypeList/"+id);
  }
}
