import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITicketReason } from 'app/shared/model/ticket-reason.model';

type EntityResponseType = HttpResponse<ITicketReason>;
type EntityArrayResponseType = HttpResponse<ITicketReason[]>;

@Injectable({ providedIn: 'root' })
export class TicketReasonService {
  public resourceUrl = SERVER_API_URL + 'api/ticket-reasons';

  constructor(protected http: HttpClient) {}

  create(ticketReason: ITicketReason): Observable<EntityResponseType> {
    return this.http.post<ITicketReason>(this.resourceUrl, ticketReason, { observe: 'response' });
  }

  update(ticketReason: ITicketReason): Observable<EntityResponseType> {
    return this.http.put<ITicketReason>(this.resourceUrl, ticketReason, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITicketReason>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITicketReason[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
