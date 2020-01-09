export interface ITicketReason {
  id?: number;
  shippingClaimId?: number;
}

export class TicketReason implements ITicketReason {
  constructor(public id?: number, public shippingClaimId?: number) {}
}
