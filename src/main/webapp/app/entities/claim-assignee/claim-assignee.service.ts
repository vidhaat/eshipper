import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimAssignee } from 'app/shared/model/claim-assignee.model';

type EntityResponseType = HttpResponse<IClaimAssignee>;
type EntityArrayResponseType = HttpResponse<IClaimAssignee[]>;

@Injectable({ providedIn: 'root' })
export class ClaimAssigneeService {
  public resourceUrl = SERVER_API_URL + 'api/claim-assignees';

  constructor(protected http: HttpClient) {}

  create(claimAssignee: IClaimAssignee): Observable<EntityResponseType> {
    return this.http.post<IClaimAssignee>(this.resourceUrl, claimAssignee, { observe: 'response' });
  }

  update(claimAssignee: IClaimAssignee): Observable<EntityResponseType> {
    return this.http.put<IClaimAssignee>(this.resourceUrl, claimAssignee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClaimAssignee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClaimAssignee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
