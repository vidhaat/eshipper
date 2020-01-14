import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';

export interface ICurrency {
  id?: number;
  name?: string;
  claimCarrierRefunds?: IClaimCarrierRefund[];
  claimEshipperRefunds?: IClaimEshipperRefund[];
}

export class Currency implements ICurrency {
  constructor(
    public id?: number,
    public name?: string,
    public claimCarrierRefunds?: IClaimCarrierRefund[],
    public claimEshipperRefunds?: IClaimEshipperRefund[]
  ) {}
}
