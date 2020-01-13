import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';

type EntityResponseType = HttpResponse<IClaimEshipperRefund>;
type EntityArrayResponseType = HttpResponse<IClaimEshipperRefund[]>;

@Injectable({ providedIn: 'root' })
export class ClaimEshipperRefundService {
  public resourceUrl = SERVER_API_URL + 'api/claim-eshipper-refunds';

  constructor(protected http: HttpClient) {}

  create(claimEshipperRefund: IClaimEshipperRefund): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(claimEshipperRefund);
    return this.http
      .post<IClaimEshipperRefund>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(claimEshipperRefund: IClaimEshipperRefund): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(claimEshipperRefund);
    return this.http
      .put<IClaimEshipperRefund>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IClaimEshipperRefund>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IClaimEshipperRefund[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(claimEshipperRefund: IClaimEshipperRefund): IClaimEshipperRefund {
    const copy: IClaimEshipperRefund = Object.assign({}, claimEshipperRefund, {
      date: claimEshipperRefund.date && claimEshipperRefund.date.isValid() ? claimEshipperRefund.date.toJSON() : undefined
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
      res.body.forEach((claimEshipperRefund: IClaimEshipperRefund) => {
        claimEshipperRefund.date = claimEshipperRefund.date ? moment(claimEshipperRefund.date) : undefined;
      });
    }
    return res;
  }
}
