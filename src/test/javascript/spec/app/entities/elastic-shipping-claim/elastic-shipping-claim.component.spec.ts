import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ElasticShippingClaimComponent } from 'app/entities/elastic-shipping-claim/elastic-shipping-claim.component';
import { ElasticShippingClaimService } from 'app/entities/elastic-shipping-claim/elastic-shipping-claim.service';
import { ElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';

describe('Component Tests', () => {
  describe('ElasticShippingClaim Management Component', () => {
    let comp: ElasticShippingClaimComponent;
    let fixture: ComponentFixture<ElasticShippingClaimComponent>;
    let service: ElasticShippingClaimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticShippingClaimComponent],
        providers: []
      })
        .overrideTemplate(ElasticShippingClaimComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticShippingClaimComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticShippingClaimService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ElasticShippingClaim(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.elasticShippingClaims && comp.elasticShippingClaims[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
