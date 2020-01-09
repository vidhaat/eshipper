import { Moment } from 'moment';
import { ICurrency } from 'app/shared/model/currency.model';

export interface IClaimEshipperRefund {
  id?: number;
  amount?: number;
  cheque?: string;
  date?: Moment;
  currencies?: ICurrency[];
}

export class ClaimEshipperRefund implements IClaimEshipperRefund {
  constructor(public id?: number, public amount?: number, public cheque?: string, public date?: Moment, public currencies?: ICurrency[]) {}
}
