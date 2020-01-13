import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCarrierRefundStatusComponent } from 'app/entities/claim-carrier-refund-status/claim-carrier-refund-status.component';
import { ClaimCarrierRefundStatusService } from 'app/entities/claim-carrier-refund-status/claim-carrier-refund-status.service';
import { ClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';

describe('Component Tests', () => {
  describe('ClaimCarrierRefundStatus Management Component', () => {
    let comp: ClaimCarrierRefundStatusComponent;
    let fixture: ComponentFixture<ClaimCarrierRefundStatusComponent>;
    let service: ClaimCarrierRefundStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCarrierRefundStatusComponent],
        providers: []
      })
        .overrideTemplate(ClaimCarrierRefundStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimCarrierRefundStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimCarrierRefundStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimCarrierRefundStatus(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimCarrierRefundStatuses && comp.claimCarrierRefundStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
