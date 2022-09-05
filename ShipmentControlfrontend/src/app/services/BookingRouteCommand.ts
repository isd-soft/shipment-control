export interface BookingRouteCommand {
  cargoDescription: String,
  /*maxLoadWeight: number,
  maxLoadVolume: number,*/
  cargoType: String[],
  weight: number,
  volume: number
}
