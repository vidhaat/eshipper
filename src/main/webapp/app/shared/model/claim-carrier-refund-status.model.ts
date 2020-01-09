export interface IClaimCarrierRefundStatus {
  id?: number;
  status?: string;
  claimCarrierRefundId?: number;
}

export class ClaimCarrierRefundStatus implements IClaimCarrierRefundStatus {
  constructor(public id?: number, public status?: string, public claimCarrierRefundId?: number) {}
}
