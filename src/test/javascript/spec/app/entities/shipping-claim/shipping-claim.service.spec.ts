import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';
import { IShippingClaim, ShippingClaim } from 'app/shared/model/shipping-claim.model';

describe('Service Tests', () => {
  describe('ShippingClaim Service', () => {
    let injector: TestBed;
    let service: ShippingClaimService;
    let httpMock: HttpTestingController;
    let elemDefault: IShippingClaim;
    let expectedResult: IShippingClaim | IShippingClaim[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ShippingClaimService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ShippingClaim(0, currentDate, currentDate, currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            mailedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            updatedDate: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a ShippingClaim', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            mailedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            updatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            receivedDate: currentDate,
            mailedDate: currentDate,
            createdDate: currentDate,
            updatedDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new ShippingClaim())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ShippingClaim', () => {
        const returnedFromService = Object.assign(
          {
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            mailedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            updatedDate: currentDate.format(DATE_TIME_FORMAT),
            trackingNumber: 'BBBBBB',
            subject: 'BBBBBB',
            description: 'BBBBBB',
            notifyCustomer: true,
            missingDocuments: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            receivedDate: currentDate,
            mailedDate: currentDate,
            createdDate: currentDate,
            updatedDate: currentDate
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

      it('should return a list of ShippingClaim', () => {
        const returnedFromService = Object.assign(
          {
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            mailedDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            updatedDate: currentDate.format(DATE_TIME_FORMAT),
            trackingNumber: 'BBBBBB',
            subject: 'BBBBBB',
            description: 'BBBBBB',
            notifyCustomer: true,
            missingDocuments: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            receivedDate: currentDate,
            mailedDate: currentDate,
            createdDate: currentDate,
            updatedDate: currentDate
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

      it('should delete a ShippingClaim', () => {
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
