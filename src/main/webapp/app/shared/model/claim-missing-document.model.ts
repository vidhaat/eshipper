import { IClaimDocumentType } from 'app/shared/model/claim-document-type.model';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IClaimMissingDocument {
  id?: number;
  documentName?: string;
  notifyClient?: boolean;
  uploaded?: boolean;
  claimDocumentTypes?: IClaimDocumentType[];
  shippingClaims?: IShippingClaim[];
}

export class ClaimMissingDocument implements IClaimMissingDocument {
  constructor(
    public id?: number,
    public documentName?: string,
    public notifyClient?: boolean,
    public uploaded?: boolean,
    public claimDocumentTypes?: IClaimDocumentType[],
    public shippingClaims?: IShippingClaim[]
  ) {
    this.notifyClient = this.notifyClient || false;
    this.uploaded = this.uploaded || false;
  }
}
