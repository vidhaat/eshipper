import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface ITicketReason {
  id?: number;
  shippingClaims?: IShippingClaim[];
}

export class TicketReason implements ITicketReason {
  constructor(public id?: number, public shippingClaims?: IShippingClaim[]) {}
}
