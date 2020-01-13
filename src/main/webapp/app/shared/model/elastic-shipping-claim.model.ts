export interface IElasticShippingClaim {
  id?: number;
  shippingClaimId?: number;
  elasticStatusId?: number;
}

export class ElasticShippingClaim implements IElasticShippingClaim {
  constructor(public id?: number, public shippingClaimId?: number, public elasticStatusId?: number) {}
}
