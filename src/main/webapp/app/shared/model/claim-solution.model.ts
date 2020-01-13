export interface IClaimSolution {
  id?: number;
  name?: string;
}

export class ClaimSolution implements IClaimSolution {
  constructor(public id?: number, public name?: string) {}
}
