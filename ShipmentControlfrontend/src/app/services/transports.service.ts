import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TransportDto} from "../model/transport.dto";


@Injectable({
  providedIn: 'root'
})
export class TransportsService {

  constructor(private httpClient: HttpClient) {
  }


  getTransports() {
    return this.httpClient.get<TransportDto[]>('http://localhost:8080/api/transports/'
      + localStorage.getItem("username"));
  }
}
