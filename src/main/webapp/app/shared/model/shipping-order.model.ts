export interface IShippingOrder {
  id?: number;
}

export class ShippingOrder implements IShippingOrder {
  constructor(public id?: number) {}
}
