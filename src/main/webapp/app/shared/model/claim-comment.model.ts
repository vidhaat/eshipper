import { Moment } from 'moment';

export interface IClaimComment {
  id?: number;
  header?: string;
  description?: string;
  date?: Moment;
  commentBy?: string;
  shippingClaimId?: number;
  user1Id?: number;
}

export class ClaimComment implements IClaimComment {
  constructor(
    public id?: number,
    public header?: string,
    public description?: string,
    public date?: Moment,
    public commentBy?: string,
    public shippingClaimId?: number,
    public user1Id?: number
  ) {}
}
