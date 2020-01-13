import { Moment } from 'moment';

export interface IClaimCarrierRefund {
  id?: number;
  carrierClaim?: string;
  amount?: number;
  chequeNumber?: string;
  cheque?: string;
  date?: Moment;
  currencyId?: number;
  refundStatusId?: number;
}

export class ClaimCarrierRefund implements IClaimCarrierRefund {
  constructor(
    public id?: number,
    public carrierClaim?: string,
    public amount?: number,
    public chequeNumber?: string,
    public cheque?: string,
    public date?: Moment,
    public currencyId?: number,
    public refundStatusId?: number
  ) {}
}
