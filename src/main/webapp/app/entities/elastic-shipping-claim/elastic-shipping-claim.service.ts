import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';

type EntityResponseType = HttpResponse<IElasticShippingClaim>;
type EntityArrayResponseType = HttpResponse<IElasticShippingClaim[]>;

@Injectable({ providedIn: 'root' })
export class ElasticShippingClaimService {
  public resourceUrl = SERVER_API_URL + 'api/elastic-shipping-claims';

  constructor(protected http: HttpClient) {}

  create(elasticShippingClaim: IElasticShippingClaim): Observable<EntityResponseType> {
    return this.http.post<IElasticShippingClaim>(this.resourceUrl, elasticShippingClaim, { observe: 'response' });
  }

  update(elasticShippingClaim: IElasticShippingClaim): Observable<EntityResponseType> {
    return this.http.put<IElasticShippingClaim>(this.resourceUrl, elasticShippingClaim, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IElasticShippingClaim>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IElasticShippingClaim[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
