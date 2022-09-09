import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CargoChatMessageLogDto} from "../model/cargoChatMessageLogDto";
import {CargoChatMessageLogCommand} from "./CargoChatMessageLogCommand";


@Injectable({
  providedIn: 'root'
})
export class CargoChatMessageLogService {
  constructor(private http: HttpClient) {
  }

  getCargoChatLogs(id: number): Observable<CargoChatMessageLogDto[]> {
    return this.http.get<CargoChatMessageLogDto[]>("http://localhost:8080/api/chat-log/" + id);
  }
  deleteCargoChatLogs(id: number) {
    return this.http.delete("http://localhost:8080/api/chat-log/" + id);
  }
  addCargoChatLogs(cargoChatLogCommand: CargoChatMessageLogCommand) {
    return this.http.post("http://localhost:8080/api/chat-log", cargoChatLogCommand);
  }
  updateCargoChatLogs(id: number, cargoChatLogCommand: CargoChatMessageLogCommand){
    return this.http.put("http://localhost:8080/api/chat-log/" + id, cargoChatLogCommand);
  }


}
