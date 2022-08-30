import {LegCommand} from "./LegCommand";

export interface ItineraryCommand {
  estimatedAmountTimeShipment:number;
  legList:LegCommand[];
}

