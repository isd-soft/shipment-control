export interface BookingRequestDto {
  bookingRequestId: number;
  routeId: number;
  routeDescription: string;
  shipmentCompanyName: string;
  goodsCompanyName: string;
  localDateRequested: Date;
}
