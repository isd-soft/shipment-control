import {ItineraryDto} from "./itinerary.dto";
import {CargoTypeDto} from "./cargoType.dto";

export interface CargoDto{
    id: number;
    trackingNumber: string;
    totalVolume: number;
    totalWeight: number;
    origin: string;
    destination: string;
    cargoStatus: string;
    itineraryDTO: ItineraryDto;
    cargoTypes: CargoTypeDto[];
    bookingDate: string;
    userCompanyName:string;
    providerCompanyName:string;

}
