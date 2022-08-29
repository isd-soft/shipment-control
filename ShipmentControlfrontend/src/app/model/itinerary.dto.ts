import {LegDto} from "./leg.dto";

export interface ItineraryDto{
  legDTOS:LegDto[];
  executionTime:number;
}
