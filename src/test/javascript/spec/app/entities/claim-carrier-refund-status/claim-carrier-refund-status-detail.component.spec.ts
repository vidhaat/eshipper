import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCarrierRefundStatusDetailComponent } from 'app/entities/claim-carrier-refund-status/claim-carrier-refund-status-detail.component';
import { ClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';

describe('Component Tests', () => {
  describe('ClaimCarrierRefundStatus Management Detail Component', () => {
    let comp: ClaimCarrierRefundStatusDetailComponent;
    let fixture: ComponentFixture<ClaimCarrierRefundStatusDetailComponent>;
    const route = ({ data: of({ claimCarrierRefundStatus: new ClaimCarrierRefundStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCarrierRefundStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimCarrierRefundStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimCarrierRefundStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimCarrierRefundStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimCarrierRefundStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
