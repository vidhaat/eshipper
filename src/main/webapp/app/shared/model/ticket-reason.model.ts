export interface ITicketReason {
  id?: number;
}

export class TicketReason implements ITicketReason {
  constructor(public id?: number) {}
}
