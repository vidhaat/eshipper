export interface IClaimStatus {
  id?: number;
  name?: string;
}

export class ClaimStatus implements IClaimStatus {
  constructor(public id?: number, public name?: string) {}
}
