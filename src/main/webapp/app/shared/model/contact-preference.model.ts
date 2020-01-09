export interface IContactPreference {
  id?: number;
  userId?: number;
  shippingClaimId?: number;
}

export class ContactPreference implements IContactPreference {
  constructor(public id?: number, public userId?: number, public shippingClaimId?: number) {}
}
