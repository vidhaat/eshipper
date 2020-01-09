export interface IClaimAssignee {
  id?: number;
  name?: string;
  shippingClaimId?: number;
}

export class ClaimAssignee implements IClaimAssignee {
  constructor(public id?: number, public name?: string, public shippingClaimId?: number) {}
}
