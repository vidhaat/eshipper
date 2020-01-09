export interface ICurrency {
  id?: number;
  name?: string;
  claimCarrierRefundId?: number;
  claimEshipperRefundId?: number;
}

export class Currency implements ICurrency {
  constructor(public id?: number, public name?: string, public claimCarrierRefundId?: number, public claimEshipperRefundId?: number) {}
}
