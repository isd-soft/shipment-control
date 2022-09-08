import {CargoTypeDto} from "../model/cargoType.dto";
import {ItineraryCommand} from "./ItineraryCommand";

export interface CargoCommand{

    trackingNumber: string;
    totalVolume: number;
    totalWeight: number;
    origin: string;
    destination: string;
    cargoStatus: string;
    itineraryCommand: ItineraryCommand;
    cargoTypes: CargoTypeDto[];
    bookingDate: string;
    routeId:number;

}
