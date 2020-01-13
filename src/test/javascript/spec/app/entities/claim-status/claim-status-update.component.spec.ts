import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimStatusUpdateComponent } from 'app/entities/claim-status/claim-status-update.component';
import { ClaimStatusService } from 'app/entities/claim-status/claim-status.service';
import { ClaimStatus } from 'app/shared/model/claim-status.model';

describe('Component Tests', () => {
  describe('ClaimStatus Management Update Component', () => {
    let comp: ClaimStatusUpdateComponent;
    let fixture: ComponentFixture<ClaimStatusUpdateComponent>;
    let service: ClaimStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimStatusUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimStatus(123);
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
        const entity = new ClaimStatus();
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
