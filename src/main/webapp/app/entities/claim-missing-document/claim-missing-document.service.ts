import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';

type EntityResponseType = HttpResponse<IClaimMissingDocument>;
type EntityArrayResponseType = HttpResponse<IClaimMissingDocument[]>;

@Injectable({ providedIn: 'root' })
export class ClaimMissingDocumentService {
  public resourceUrl = SERVER_API_URL + 'api/claim-missing-documents';

  constructor(protected http: HttpClient) {}

  create(claimMissingDocument: IClaimMissingDocument): Observable<EntityResponseType> {
    return this.http.post<IClaimMissingDocument>(this.resourceUrl, claimMissingDocument, { observe: 'response' });
  }

  update(claimMissingDocument: IClaimMissingDocument): Observable<EntityResponseType> {
    return this.http.put<IClaimMissingDocument>(this.resourceUrl, claimMissingDocument, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClaimMissingDocument>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClaimMissingDocument[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
