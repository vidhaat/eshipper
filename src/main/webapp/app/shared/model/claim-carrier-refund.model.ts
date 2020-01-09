import { Moment } from 'moment';
import { ICurrency } from 'app/shared/model/currency.model';
import { IClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';

export interface IClaimCarrierRefund {
  id?: number;
  carrierClaim?: string;
  amount?: number;
  chequeNumber?: string;
  cheque?: string;
  date?: Moment;
  currencies?: ICurrency[];
  refundStatuses?: IClaimCarrierRefundStatus[];
}

export class ClaimCarrierRefund implements IClaimCarrierRefund {
  constructor(
    public id?: number,
    public carrierClaim?: string,
    public amount?: number,
    public chequeNumber?: string,
    public cheque?: string,
    public date?: Moment,
    public currencies?: ICurrency[],
    public refundStatuses?: IClaimCarrierRefundStatus[]
  ) {}
}
