import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShippingClaimUpdateComponent } from 'app/entities/shipping-claim/shipping-claim-update.component';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';
import { ShippingClaim } from 'app/shared/model/shipping-claim.model';

describe('Component Tests', () => {
  describe('ShippingClaim Management Update Component', () => {
    let comp: ShippingClaimUpdateComponent;
    let fixture: ComponentFixture<ShippingClaimUpdateComponent>;
    let service: ShippingClaimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShippingClaimUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ShippingClaimUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShippingClaimUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShippingClaimService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShippingClaim(123);
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
        const entity = new ShippingClaim();
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
