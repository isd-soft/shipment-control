import {CargoTypeDto} from "../model/cargoType.dto";

export interface TransportCommand {
  transportName: string;
  transportType: string;
  cargoTypes: number[];
}
