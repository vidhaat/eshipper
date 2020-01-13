import { Moment } from 'moment';

export interface IClaimEshipperRefund {
  id?: number;
  amount?: number;
  cheque?: string;
  date?: Moment;
  currencyId?: number;
}

export class ClaimEshipperRefund implements IClaimEshipperRefund {
  constructor(public id?: number, public amount?: number, public cheque?: string, public date?: Moment, public currencyId?: number) {}
}
