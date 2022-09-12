import {ItineraryCommand} from "./ItineraryCommand";

export interface RouteCommand {
  detailedRouteDescription :string;
  transportIdList :string[];
  itineraryCommand: ItineraryCommand;
  availableDaysRentList :string[];
  maxLoadWeight :string;
  maxLoadVolume :string;
  currency: string;
}
