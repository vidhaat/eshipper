export interface IElasticsearchStatus {
  id?: number;
  elasticShippingClaimId?: number;
}

export class ElasticsearchStatus implements IElasticsearchStatus {
  constructor(public id?: number, public elasticShippingClaimId?: number) {}
}
