import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticShippingClaimDetailComponent } from 'app/entities/elastic-shipping-claim/elastic-shipping-claim-detail.component';
import { ElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';

describe('Component Tests', () => {
  describe('ElasticShippingClaim Management Detail Component', () => {
    let comp: ElasticShippingClaimDetailComponent;
    let fixture: ComponentFixture<ElasticShippingClaimDetailComponent>;
    const route = ({ data: of({ elasticShippingClaim: new ElasticShippingClaim(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticShippingClaimDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ElasticShippingClaimDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticShippingClaimDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load elasticShippingClaim on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.elasticShippingClaim).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
