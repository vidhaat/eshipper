export interface IClaimDocumentType {
  id?: number;
  name?: string;
}

export class ClaimDocumentType implements IClaimDocumentType {
  constructor(public id?: number, public name?: string) {}
}
