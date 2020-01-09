import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimStatus } from 'app/shared/model/claim-status.model';

type EntityResponseType = HttpResponse<IClaimStatus>;
type EntityArrayResponseType = HttpResponse<IClaimStatus[]>;

@Injectable({ providedIn: 'root' })
export class ClaimStatusService {
  public resourceUrl = SERVER_API_URL + 'api/claim-statuses';

  constructor(protected http: HttpClient) {}

  create(claimStatus: IClaimStatus): Observable<EntityResponseType> {
    return this.http.post<IClaimStatus>(this.resourceUrl, claimStatus, { observe: 'response' });
  }

  update(claimStatus: IClaimStatus): Observable<EntityResponseType> {
    return this.http.put<IClaimStatus>(this.resourceUrl, claimStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClaimStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClaimStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
