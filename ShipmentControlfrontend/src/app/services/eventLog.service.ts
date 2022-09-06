import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EventLogDto} from "../model/eventLog.Dto";

@Injectable({
  providedIn: 'root'
})
export class EventLogService {

  constructor(private http: HttpClient) {
  }

  getEventLogs(trackingNumber: string) {
    return this.http.get<EventLogDto[]>("http://localhost:8080/api/events/" + trackingNumber)
  }
}

