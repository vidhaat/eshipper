export interface IElasticsearchStatus {
  id?: number;
}

export class ElasticsearchStatus implements IElasticsearchStatus {
  constructor(public id?: number) {}
}
