import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimEshipperRefundComponent } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund.component';
import { ClaimEshipperRefundService } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund.service';
import { ClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';

describe('Component Tests', () => {
  describe('ClaimEshipperRefund Management Component', () => {
    let comp: ClaimEshipperRefundComponent;
    let fixture: ComponentFixture<ClaimEshipperRefundComponent>;
    let service: ClaimEshipperRefundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimEshipperRefundComponent],
        providers: []
      })
        .overrideTemplate(ClaimEshipperRefundComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimEshipperRefundComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimEshipperRefundService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimEshipperRefund(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimEshipperRefunds && comp.claimEshipperRefunds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
