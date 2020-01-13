import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimSolutionUpdateComponent } from 'app/entities/claim-solution/claim-solution-update.component';
import { ClaimSolutionService } from 'app/entities/claim-solution/claim-solution.service';
import { ClaimSolution } from 'app/shared/model/claim-solution.model';

describe('Component Tests', () => {
  describe('ClaimSolution Management Update Component', () => {
    let comp: ClaimSolutionUpdateComponent;
    let fixture: ComponentFixture<ClaimSolutionUpdateComponent>;
    let service: ClaimSolutionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimSolutionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimSolutionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimSolutionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimSolutionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimSolution(123);
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
        const entity = new ClaimSolution();
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
