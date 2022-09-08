import {ItineraryDto} from "../model/itinerary.dto";
import {CargoTypeDto} from "../model/cargoType.dto";

export interface CargoCommand{

    trackingNumber: string;
    totalVolume: number;
    totalWeight: number;
    origin: string;
    destination: string;
    cargoStatus: string[];
    itineraryDTO: ItineraryDto;
    cargoTypes: CargoTypeDto[];
    bookingDate: string;


}