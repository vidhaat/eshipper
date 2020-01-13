export interface IClaimCarrierRefundStatus {
  id?: number;
  status?: string;
}

export class ClaimCarrierRefundStatus implements IClaimCarrierRefundStatus {
  constructor(public id?: number, public status?: string) {}
}
