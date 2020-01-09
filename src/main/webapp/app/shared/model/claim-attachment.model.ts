import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IClaimAttachment {
  id?: number;
  attachmentPath?: string;
  shippingClaims?: IShippingClaim[];
}

export class ClaimAttachment implements IClaimAttachment {
  constructor(public id?: number, public attachmentPath?: string, public shippingClaims?: IShippingClaim[]) {}
}
