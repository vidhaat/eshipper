import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IClaimAssignee {
  id?: number;
  name?: string;
  shippingClaims?: IShippingClaim[];
}

export class ClaimAssignee implements IClaimAssignee {
  constructor(public id?: number, public name?: string, public shippingClaims?: IShippingClaim[]) {}
}
