import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IClaimStatus {
  id?: number;
  name?: string;
  shippingClaims?: IShippingClaim[];
}

export class ClaimStatus implements IClaimStatus {
  constructor(public id?: number, public name?: string, public shippingClaims?: IShippingClaim[]) {}
}
