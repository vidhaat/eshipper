import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCommentUpdateComponent } from 'app/entities/claim-comment/claim-comment-update.component';
import { ClaimCommentService } from 'app/entities/claim-comment/claim-comment.service';
import { ClaimComment } from 'app/shared/model/claim-comment.model';

describe('Component Tests', () => {
  describe('ClaimComment Management Update Component', () => {
    let comp: ClaimCommentUpdateComponent;
    let fixture: ComponentFixture<ClaimCommentUpdateComponent>;
    let service: ClaimCommentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCommentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimCommentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimCommentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimCommentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimComment(123);
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
        const entity = new ClaimComment();
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
