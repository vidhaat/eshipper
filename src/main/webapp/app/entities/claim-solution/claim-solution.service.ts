import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimSolution } from 'app/shared/model/claim-solution.model';

type EntityResponseType = HttpResponse<IClaimSolution>;
type EntityArrayResponseType = HttpResponse<IClaimSolution[]>;

@Injectable({ providedIn: 'root' })
export class ClaimSolutionService {
  public resourceUrl = SERVER_API_URL + 'api/claim-solutions';

  constructor(protected http: HttpClient) {}

  create(claimSolution: IClaimSolution): Observable<EntityResponseType> {
    return this.http.post<IClaimSolution>(this.resourceUrl, claimSolution, { observe: 'response' });
  }

  update(claimSolution: IClaimSolution): Observable<EntityResponseType> {
    return this.http.put<IClaimSolution>(this.resourceUrl, claimSolution, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClaimSolution>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClaimSolution[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
