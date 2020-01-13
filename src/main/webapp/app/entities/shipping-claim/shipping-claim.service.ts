import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';

type EntityResponseType = HttpResponse<IShippingClaim>;
type EntityArrayResponseType = HttpResponse<IShippingClaim[]>;

@Injectable({ providedIn: 'root' })
export class ShippingClaimService {
  public resourceUrl = SERVER_API_URL + 'api/shipping-claims';

  constructor(protected http: HttpClient) {}

  create(shippingClaim: IShippingClaim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shippingClaim);
    return this.http
      .post<IShippingClaim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(shippingClaim: IShippingClaim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shippingClaim);
    return this.http
      .put<IShippingClaim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IShippingClaim>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IShippingClaim[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(shippingClaim: IShippingClaim): IShippingClaim {
    const copy: IShippingClaim = Object.assign({}, shippingClaim, {
      receivedDate: shippingClaim.receivedDate && shippingClaim.receivedDate.isValid() ? shippingClaim.receivedDate.toJSON() : undefined,
      mailedDate: shippingClaim.mailedDate && shippingClaim.mailedDate.isValid() ? shippingClaim.mailedDate.toJSON() : undefined,
      createdDate: shippingClaim.createdDate && shippingClaim.createdDate.isValid() ? shippingClaim.createdDate.toJSON() : undefined,
      updatedDate: shippingClaim.updatedDate && shippingClaim.updatedDate.isValid() ? shippingClaim.updatedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.receivedDate = res.body.receivedDate ? moment(res.body.receivedDate) : undefined;
      res.body.mailedDate = res.body.mailedDate ? moment(res.body.mailedDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.updatedDate = res.body.updatedDate ? moment(res.body.updatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((shippingClaim: IShippingClaim) => {
        shippingClaim.receivedDate = shippingClaim.receivedDate ? moment(shippingClaim.receivedDate) : undefined;
        shippingClaim.mailedDate = shippingClaim.mailedDate ? moment(shippingClaim.mailedDate) : undefined;
        shippingClaim.createdDate = shippingClaim.createdDate ? moment(shippingClaim.createdDate) : undefined;
        shippingClaim.updatedDate = shippingClaim.updatedDate ? moment(shippingClaim.updatedDate) : undefined;
      });
    }
    return res;
  }
}
