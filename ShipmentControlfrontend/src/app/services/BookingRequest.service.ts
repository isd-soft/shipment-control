import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookingRequestCommand} from "./BookingRequestCommand";


@Injectable({
  providedIn: 'root'
})
export class BookingRequestService {
  constructor(private http: HttpClient) {
  }

  addBookingRequest(data: BookingRequestCommand) {
    return this.http.post("http://localhost:8080/api/booking-request", data);
  }

  getBookingRequest(): Observable<BookingRequestCommand[]> {
    return this.http.get<BookingRequestCommand[]>("http://localhost:8080/api/booking-request")
  }
}
