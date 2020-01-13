import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCarrierRefundComponent } from 'app/entities/claim-carrier-refund/claim-carrier-refund.component';
import { ClaimCarrierRefundService } from 'app/entities/claim-carrier-refund/claim-carrier-refund.service';
import { ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

describe('Component Tests', () => {
  describe('ClaimCarrierRefund Management Component', () => {
    let comp: ClaimCarrierRefundComponent;
    let fixture: ComponentFixture<ClaimCarrierRefundComponent>;
    let service: ClaimCarrierRefundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCarrierRefundComponent],
        providers: []
      })
        .overrideTemplate(ClaimCarrierRefundComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimCarrierRefundComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimCarrierRefundService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimCarrierRefund(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimCarrierRefunds && comp.claimCarrierRefunds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
