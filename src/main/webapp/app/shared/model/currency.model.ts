export interface ICurrency {
  id?: number;
  name?: string;
}

export class Currency implements ICurrency {
  constructor(public id?: number, public name?: string) {}
}
