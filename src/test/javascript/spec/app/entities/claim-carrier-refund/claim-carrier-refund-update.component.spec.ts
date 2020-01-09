import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCarrierRefundUpdateComponent } from 'app/entities/claim-carrier-refund/claim-carrier-refund-update.component';
import { ClaimCarrierRefundService } from 'app/entities/claim-carrier-refund/claim-carrier-refund.service';
import { ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

describe('Component Tests', () => {
  describe('ClaimCarrierRefund Management Update Component', () => {
    let comp: ClaimCarrierRefundUpdateComponent;
    let fixture: ComponentFixture<ClaimCarrierRefundUpdateComponent>;
    let service: ClaimCarrierRefundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCarrierRefundUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimCarrierRefundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimCarrierRefundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimCarrierRefundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimCarrierRefund(123);
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
        const entity = new ClaimCarrierRefund();
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
