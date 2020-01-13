import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShippingClaimDetailComponent } from 'app/entities/shipping-claim/shipping-claim-detail.component';
import { ShippingClaim } from 'app/shared/model/shipping-claim.model';

describe('Component Tests', () => {
  describe('ShippingClaim Management Detail Component', () => {
    let comp: ShippingClaimDetailComponent;
    let fixture: ComponentFixture<ShippingClaimDetailComponent>;
    const route = ({ data: of({ shippingClaim: new ShippingClaim(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShippingClaimDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ShippingClaimDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShippingClaimDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load shippingClaim on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shippingClaim).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
