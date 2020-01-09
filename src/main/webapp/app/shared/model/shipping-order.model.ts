export interface IShippingOrder {
  id?: number;
  shippingClaimId?: number;
}

export class ShippingOrder implements IShippingOrder {
  constructor(public id?: number, public shippingClaimId?: number) {}
}
