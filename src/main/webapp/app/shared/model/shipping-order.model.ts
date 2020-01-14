import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IShippingOrder {
  id?: number;
  shippingClaims?: IShippingClaim[];
}

export class ShippingOrder implements IShippingOrder {
  constructor(public id?: number, public shippingClaims?: IShippingClaim[]) {}
}
