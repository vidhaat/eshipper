export interface IClaimAssignee {
  id?: number;
  name?: string;
}

export class ClaimAssignee implements IClaimAssignee {
  constructor(public id?: number, public name?: string) {}
}
