import {TransportDto} from "./transport.dto";
import {ItineraryDto} from "./itinerary.dto";
import {AvailableDaysRentDto} from "./availableDaysRentDto";


export interface RouteDto {
  routeId: number;
  userId: number;
  transportDTOList: TransportDto[];
  itineraryDTO: ItineraryDto;
  availableDaysRentList: AvailableDaysRentDto[];
  maximalLoadWeight: number;
  maxLoadVolume: number;
  estimatedAmountTimeShipment: number;
  routeDescription: string;
  providerCompany: string;
}

