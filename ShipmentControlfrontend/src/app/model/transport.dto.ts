import {CargoTypeDto} from "./cargoType.dto";

export interface TransportDto {
  transportName: string;
  cargoTypes: CargoTypeDto[];
  transportType: string;
  transportId: number;
}
