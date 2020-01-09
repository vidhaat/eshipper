import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IClaimComment {
  id?: number;
  header?: string;
  description?: string;
  date?: Moment;
  commentBy?: string;
  users?: IUser[];
  shippingClaimId?: number;
}

export class ClaimComment implements IClaimComment {
  constructor(
    public id?: number,
    public header?: string,
    public description?: string,
    public date?: Moment,
    public commentBy?: string,
    public users?: IUser[],
    public shippingClaimId?: number
  ) {}
}
