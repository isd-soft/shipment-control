import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CargoOverviewDTO} from "../model/cargoOverview.dto";
import {CargoDto} from "../model/cargo.dto";

@Injectable({
  providedIn: 'root'
})
export class CargoOverviewService {

  constructor(private http: HttpClient) {
  }

  // getCargoOverview(provider: string): Observable<ArrayBuffer> {
  //   // @ts-ignore
  //   return this.http.get<CargoOverviewDTO[]>("http://localhost:8080/api/cargoOverview/provider", provider)
  // }

  getCargoOverview(): Observable<CargoOverviewDTO[]> {
    return this.http.get<CargoOverviewDTO[]>("http://localhost:8080/api/cargoOverview")
  }
}
