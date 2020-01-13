export interface IClaimAttachment {
  id?: number;
  attachmentPath?: string;
  shippingClaimId?: number;
}

export class ClaimAttachment implements IClaimAttachment {
  constructor(public id?: number, public attachmentPath?: string, public shippingClaimId?: number) {}
}
