import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TransportDto} from "../model/transport.dto";
import {TransportCommand} from "./TransportCommand";


@Injectable({
  providedIn: 'root'
})
export class TransportsService {

  constructor(private httpClient: HttpClient) {
  }


  getTransports() {
    return this.httpClient.get<TransportDto[]>('http://localhost:8080/api/transports/');
  }

  addTransports(data: TransportCommand) {
    return this.httpClient.post<TransportCommand>("http://localhost:8080/api/transports/",data)
  }

  putTransports(data : TransportCommand, id:number) {
    return this.httpClient.put<any>("http://localhost:8080/api/transports/" + id, data)
  }
  deleteTransports(id : number){
    return this.httpClient.delete("http://localhost:8080/api/transports/"+id);
  }
}
