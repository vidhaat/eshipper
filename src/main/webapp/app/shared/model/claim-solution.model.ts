import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IClaimSolution {
  id?: number;
  name?: string;
  shippingClaims?: IShippingClaim[];
}

export class ClaimSolution implements IClaimSolution {
  constructor(public id?: number, public name?: string, public shippingClaims?: IShippingClaim[]) {}
}
