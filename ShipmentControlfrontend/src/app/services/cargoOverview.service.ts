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

  getCargoOverview(): Observable<ArrayBuffer> {
    // @ts-ignore
    return this.http.get<CargoDTO[]>("http://localhost:8080/api/cargo/")
  }
}
