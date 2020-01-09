import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContactPreference } from 'app/shared/model/contact-preference.model';

type EntityResponseType = HttpResponse<IContactPreference>;
type EntityArrayResponseType = HttpResponse<IContactPreference[]>;

@Injectable({ providedIn: 'root' })
export class ContactPreferenceService {
  public resourceUrl = SERVER_API_URL + 'api/contact-preferences';

  constructor(protected http: HttpClient) {}

  create(contactPreference: IContactPreference): Observable<EntityResponseType> {
    return this.http.post<IContactPreference>(this.resourceUrl, contactPreference, { observe: 'response' });
  }

  update(contactPreference: IContactPreference): Observable<EntityResponseType> {
    return this.http.put<IContactPreference>(this.resourceUrl, contactPreference, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IContactPreference>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContactPreference[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
