import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimAssigneeUpdateComponent } from 'app/entities/claim-assignee/claim-assignee-update.component';
import { ClaimAssigneeService } from 'app/entities/claim-assignee/claim-assignee.service';
import { ClaimAssignee } from 'app/shared/model/claim-assignee.model';

describe('Component Tests', () => {
  describe('ClaimAssignee Management Update Component', () => {
    let comp: ClaimAssigneeUpdateComponent;
    let fixture: ComponentFixture<ClaimAssigneeUpdateComponent>;
    let service: ClaimAssigneeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimAssigneeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimAssigneeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimAssigneeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimAssigneeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimAssignee(123);
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
        const entity = new ClaimAssignee();
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
