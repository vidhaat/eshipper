import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';

export interface IClaimDocumentType {
  id?: number;
  name?: string;
  claimMissingDocuments?: IClaimMissingDocument[];
}

export class ClaimDocumentType implements IClaimDocumentType {
  constructor(public id?: number, public name?: string, public claimMissingDocuments?: IClaimMissingDocument[]) {}
}
