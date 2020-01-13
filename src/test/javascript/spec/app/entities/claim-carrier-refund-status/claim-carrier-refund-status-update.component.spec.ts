import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCarrierRefundStatusUpdateComponent } from 'app/entities/claim-carrier-refund-status/claim-carrier-refund-status-update.component';
import { ClaimCarrierRefundStatusService } from 'app/entities/claim-carrier-refund-status/claim-carrier-refund-status.service';
import { ClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';

describe('Component Tests', () => {
  describe('ClaimCarrierRefundStatus Management Update Component', () => {
    let comp: ClaimCarrierRefundStatusUpdateComponent;
    let fixture: ComponentFixture<ClaimCarrierRefundStatusUpdateComponent>;
    let service: ClaimCarrierRefundStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCarrierRefundStatusUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimCarrierRefundStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimCarrierRefundStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimCarrierRefundStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimCarrierRefundStatus(123);
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
        const entity = new ClaimCarrierRefundStatus();
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
