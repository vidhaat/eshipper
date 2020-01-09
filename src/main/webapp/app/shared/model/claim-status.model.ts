export interface IClaimStatus {
  id?: number;
  name?: string;
  shippingClaimId?: number;
}

export class ClaimStatus implements IClaimStatus {
  constructor(public id?: number, public name?: string, public shippingClaimId?: number) {}
}
