import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

export interface IClaimCarrierRefundStatus {
  id?: number;
  status?: string;
  claimCarrierRefunds?: IClaimCarrierRefund[];
}

export class ClaimCarrierRefundStatus implements IClaimCarrierRefundStatus {
  constructor(public id?: number, public status?: string, public claimCarrierRefunds?: IClaimCarrierRefund[]) {}
}
