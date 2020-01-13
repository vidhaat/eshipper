import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';

type EntityResponseType = HttpResponse<IElasticsearchStatus>;
type EntityArrayResponseType = HttpResponse<IElasticsearchStatus[]>;

@Injectable({ providedIn: 'root' })
export class ElasticsearchStatusService {
  public resourceUrl = SERVER_API_URL + 'api/elasticsearch-statuses';

  constructor(protected http: HttpClient) {}

  create(elasticsearchStatus: IElasticsearchStatus): Observable<EntityResponseType> {
    return this.http.post<IElasticsearchStatus>(this.resourceUrl, elasticsearchStatus, { observe: 'response' });
  }

  update(elasticsearchStatus: IElasticsearchStatus): Observable<EntityResponseType> {
    return this.http.put<IElasticsearchStatus>(this.resourceUrl, elasticsearchStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IElasticsearchStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IElasticsearchStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
