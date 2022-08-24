import {CargoTypeDto} from "./cargoType.dto";

export interface TransportDto {
  transportName: string;
  routeId: number;
  cargoTypes: CargoTypeDto[];
  transportType: string;
  transportId: number;
}
