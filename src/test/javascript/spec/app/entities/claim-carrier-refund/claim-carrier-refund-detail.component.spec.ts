import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCarrierRefundDetailComponent } from 'app/entities/claim-carrier-refund/claim-carrier-refund-detail.component';
import { ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

describe('Component Tests', () => {
  describe('ClaimCarrierRefund Management Detail Component', () => {
    let comp: ClaimCarrierRefundDetailComponent;
    let fixture: ComponentFixture<ClaimCarrierRefundDetailComponent>;
    const route = ({ data: of({ claimCarrierRefund: new ClaimCarrierRefund(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCarrierRefundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimCarrierRefundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimCarrierRefundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimCarrierRefund on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimCarrierRefund).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
