import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

type EntityResponseType = HttpResponse<IClaimCarrierRefund>;
type EntityArrayResponseType = HttpResponse<IClaimCarrierRefund[]>;

@Injectable({ providedIn: 'root' })
export class ClaimCarrierRefundService {
  public resourceUrl = SERVER_API_URL + 'api/claim-carrier-refunds';

  constructor(protected http: HttpClient) {}

  create(claimCarrierRefund: IClaimCarrierRefund): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(claimCarrierRefund);
    return this.http
      .post<IClaimCarrierRefund>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(claimCarrierRefund: IClaimCarrierRefund): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(claimCarrierRefund);
    return this.http
      .put<IClaimCarrierRefund>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IClaimCarrierRefund>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IClaimCarrierRefund[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(claimCarrierRefund: IClaimCarrierRefund): IClaimCarrierRefund {
    const copy: IClaimCarrierRefund = Object.assign({}, claimCarrierRefund, {
      date: claimCarrierRefund.date && claimCarrierRefund.date.isValid() ? claimCarrierRefund.date.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((claimCarrierRefund: IClaimCarrierRefund) => {
        claimCarrierRefund.date = claimCarrierRefund.date ? moment(claimCarrierRefund.date) : undefined;
      });
    }
    return res;
  }
}
