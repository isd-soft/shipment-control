import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookingRequestCommand} from "./BookingRequestCommand";
import {BookingRequestDto} from "../model/bookingRequest.dto";


@Injectable({
  providedIn: 'root'
})
export class BookingRequestService {
  constructor(private http: HttpClient) {
  }

  addBookingRequest(data: BookingRequestCommand) {
    return this.http.post("http://localhost:8080/api/booking-request", data);
  }

  getBookingRequest(): Observable<BookingRequestDto[]> {
    return this.http.get<BookingRequestDto[]>("http://localhost:8080/api/booking-request")
  }

  acceptBookingRequest(id: number) {
    return this.http.delete("http://localhost:8080/api/booking-request/accept/" + id);
  }

  denyBookingRequest(id: number) {
    return this.http.delete("http://localhost:8080/api/booking-request/deny/" + id);
  }
}
