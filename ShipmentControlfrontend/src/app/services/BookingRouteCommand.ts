import {ItineraryCommand} from "./ItineraryCommand";

export interface BookingRouteCommand {
  totalVolume: number;
  totalWeight: number;
  cargoTypeList: number[];
  itineraryCommand: ItineraryCommand;
  bookingDate: string|null;
}
