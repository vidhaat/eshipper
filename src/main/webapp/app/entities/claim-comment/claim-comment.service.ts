import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClaimComment } from 'app/shared/model/claim-comment.model';

type EntityResponseType = HttpResponse<IClaimComment>;
type EntityArrayResponseType = HttpResponse<IClaimComment[]>;

@Injectable({ providedIn: 'root' })
export class ClaimCommentService {
  public resourceUrl = SERVER_API_URL + 'api/claim-comments';

  constructor(protected http: HttpClient) {}

  create(claimComment: IClaimComment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(claimComment);
    return this.http
      .post<IClaimComment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(claimComment: IClaimComment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(claimComment);
    return this.http
      .put<IClaimComment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IClaimComment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IClaimComment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(claimComment: IClaimComment): IClaimComment {
    const copy: IClaimComment = Object.assign({}, claimComment, {
      date: claimComment.date && claimComment.date.isValid() ? claimComment.date.toJSON() : undefined
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
      res.body.forEach((claimComment: IClaimComment) => {
        claimComment.date = claimComment.date ? moment(claimComment.date) : undefined;
      });
    }
    return res;
  }
}
