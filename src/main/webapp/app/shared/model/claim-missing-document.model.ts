export interface IClaimMissingDocument {
  id?: number;
  documentName?: string;
  notifyClient?: boolean;
  uploaded?: boolean;
  claimDocumentTypeId?: number;
  shippingClaimId?: number;
}

export class ClaimMissingDocument implements IClaimMissingDocument {
  constructor(
    public id?: number,
    public documentName?: string,
    public notifyClient?: boolean,
    public uploaded?: boolean,
    public claimDocumentTypeId?: number,
    public shippingClaimId?: number
  ) {
    this.notifyClient = this.notifyClient || false;
    this.uploaded = this.uploaded || false;
  }
}
