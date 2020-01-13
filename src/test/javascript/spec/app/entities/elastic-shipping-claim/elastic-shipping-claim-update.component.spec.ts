import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticShippingClaimUpdateComponent } from 'app/entities/elastic-shipping-claim/elastic-shipping-claim-update.component';
import { ElasticShippingClaimService } from 'app/entities/elastic-shipping-claim/elastic-shipping-claim.service';
import { ElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';

describe('Component Tests', () => {
  describe('ElasticShippingClaim Management Update Component', () => {
    let comp: ElasticShippingClaimUpdateComponent;
    let fixture: ComponentFixture<ElasticShippingClaimUpdateComponent>;
    let service: ElasticShippingClaimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticShippingClaimUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ElasticShippingClaimUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticShippingClaimUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticShippingClaimService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticShippingClaim(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticShippingClaim();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
