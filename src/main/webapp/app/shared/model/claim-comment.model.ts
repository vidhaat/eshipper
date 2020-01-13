import { Moment } from 'moment';

export interface IClaimComment {
  id?: number;
  header?: string;
  description?: string;
  date?: Moment;
  commentBy?: string;
  userId?: number;
}

export class ClaimComment implements IClaimComment {
  constructor(
    public id?: number,
    public header?: string,
    public description?: string,
    public date?: Moment,
    public commentBy?: string,
    public userId?: number
  ) {}
}
