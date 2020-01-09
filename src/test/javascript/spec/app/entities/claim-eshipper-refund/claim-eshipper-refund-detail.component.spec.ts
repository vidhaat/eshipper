import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimEshipperRefundDetailComponent } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund-detail.component';
import { ClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';

describe('Component Tests', () => {
  describe('ClaimEshipperRefund Management Detail Component', () => {
    let comp: ClaimEshipperRefundDetailComponent;
    let fixture: ComponentFixture<ClaimEshipperRefundDetailComponent>;
    const route = ({ data: of({ claimEshipperRefund: new ClaimEshipperRefund(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimEshipperRefundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimEshipperRefundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimEshipperRefundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimEshipperRefund on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimEshipperRefund).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
