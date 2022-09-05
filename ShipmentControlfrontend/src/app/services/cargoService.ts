import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {CargoDto} from "../model/cargo.dto";

@Injectable({
    providedIn: 'root'
})
export class CargoService {

    url = "http://localhost:8080/api/cargo";

    constructor(private http: HttpClient) {
    }

    getCargo(): Observable<CargoDto[]> {
        return this.http.get<CargoDto[]>("http://localhost:8080/api/cargo")
    }

    getCargoById(id: number) {
        return this.http.get<CargoDto>( `${this.url}/${id}` );
    }

    approveCargo(data: any, id: number){
        return this.http.put<any>("http://localhost:8080/api/cargo/approve/" + id, data);
    }

    rejectCargo(id: number){
        return this.http.delete("http://localhost:8080/api/cargo/" + id);
    }
}