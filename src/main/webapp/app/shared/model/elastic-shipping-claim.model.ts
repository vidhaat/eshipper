import { IElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';

export interface IElasticShippingClaim {
  id?: number;
  shippingClaimId?: number;
  elasticStatuses?: IElasticsearchStatus[];
}

export class ElasticShippingClaim implements IElasticShippingClaim {
  constructor(public id?: number, public shippingClaimId?: number, public elasticStatuses?: IElasticsearchStatus[]) {}
}
