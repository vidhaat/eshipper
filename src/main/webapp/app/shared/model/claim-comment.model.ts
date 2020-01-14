import { Moment } from 'moment';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IClaimComment {
  id?: number;
  header?: string;
  description?: string;
  date?: Moment;
  commentBy?: string;
  shippingClaims?: IShippingClaim[];
  user1Id?: number;
}

export class ClaimComment implements IClaimComment {
  constructor(
    public id?: number,
    public header?: string,
    public description?: string,
    public date?: Moment,
    public commentBy?: string,
    public shippingClaims?: IShippingClaim[],
    public user1Id?: number
  ) {}
}
