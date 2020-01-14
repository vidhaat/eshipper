import { IElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';

export interface IElasticsearchStatus {
  id?: number;
  elasticShippingClaims?: IElasticShippingClaim[];
}

export class ElasticsearchStatus implements IElasticsearchStatus {
  constructor(public id?: number, public elasticShippingClaims?: IElasticShippingClaim[]) {}
}
