import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ClaimCarrierRefundService } from 'app/entities/claim-carrier-refund/claim-carrier-refund.service';
import { IClaimCarrierRefund, ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

describe('Service Tests', () => {
  describe('ClaimCarrierRefund Service', () => {
    let injector: TestBed;
    let service: ClaimCarrierRefundService;
    let httpMock: HttpTestingController;
    let elemDefault: IClaimCarrierRefund;
    let expectedResult: IClaimCarrierRefund | IClaimCarrierRefund[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ClaimCarrierRefundService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ClaimCarrierRefund(0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ClaimCarrierRefund', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );
        service
          .create(new ClaimCarrierRefund())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ClaimCarrierRefund', () => {
        const returnedFromService = Object.assign(
          {
            carrierClaim: 'BBBBBB',
            amount: 1,
            chequeNumber: 'BBBBBB',
            cheque: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ClaimCarrierRefund', () => {
        const returnedFromService = Object.assign(
          {
            carrierClaim: 'BBBBBB',
            amount: 1,
            chequeNumber: 'BBBBBB',
            cheque: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ClaimCarrierRefund', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
