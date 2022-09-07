import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CargoOverviewDTO} from "../model/cargoOverview.dto";

@Injectable({
  providedIn: 'root'
})
export class CargoOverviewService {

  constructor(private http: HttpClient) {
  }

  getCargoOverview(): Observable<CargoOverviewDTO[]> {
    return this.http.get<CargoOverviewDTO[]>("http://localhost:8080/api/cargoOverview")
  }
}
