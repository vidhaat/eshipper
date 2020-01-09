export interface IClaimDocumentType {
  id?: number;
  name?: string;
  claimMissingDocumentId?: number;
}

export class ClaimDocumentType implements IClaimDocumentType {
  constructor(public id?: number, public name?: string, public claimMissingDocumentId?: number) {}
}
