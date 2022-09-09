import {LegCommand} from "./LegCommand";

export interface ItineraryCommand {
  estimatedAmountTimeShipment:number | null;
  legList:LegCommand[];
}

