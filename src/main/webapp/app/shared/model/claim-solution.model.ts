export interface IClaimSolution {
  id?: number;
  name?: string;
  shippingClaimId?: number;
}

export class ClaimSolution implements IClaimSolution {
  constructor(public id?: number, public name?: string, public shippingClaimId?: number) {}
}
