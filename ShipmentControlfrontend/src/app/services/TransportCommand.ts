export interface TransportCommand {
  transportName: string;
  transportType: string;
  cargoType: string[];
  userId: number;
  routeId: number;
}
