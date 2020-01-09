import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimEshipperRefundUpdateComponent } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund-update.component';
import { ClaimEshipperRefundService } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund.service';
import { ClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';

describe('Component Tests', () => {
  describe('ClaimEshipperRefund Management Update Component', () => {
    let comp: ClaimEshipperRefundUpdateComponent;
    let fixture: ComponentFixture<ClaimEshipperRefundUpdateComponent>;
    let service: ClaimEshipperRefundService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimEshipperRefundUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimEshipperRefundUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimEshipperRefundUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimEshipperRefundService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimEshipperRefund(123);
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
        const entity = new ClaimEshipperRefund();
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
