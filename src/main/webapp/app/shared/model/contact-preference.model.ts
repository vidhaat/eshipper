import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

export interface IContactPreference {
  id?: number;
  userId?: number;
  shippingClaims?: IShippingClaim[];
}

export class ContactPreference implements IContactPreference {
  constructor(public id?: number, public userId?: number, public shippingClaims?: IShippingClaim[]) {}
}
