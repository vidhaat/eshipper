import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimDocumentTypeUpdateComponent } from 'app/entities/claim-document-type/claim-document-type-update.component';
import { ClaimDocumentTypeService } from 'app/entities/claim-document-type/claim-document-type.service';
import { ClaimDocumentType } from 'app/shared/model/claim-document-type.model';

describe('Component Tests', () => {
  describe('ClaimDocumentType Management Update Component', () => {
    let comp: ClaimDocumentTypeUpdateComponent;
    let fixture: ComponentFixture<ClaimDocumentTypeUpdateComponent>;
    let service: ClaimDocumentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimDocumentTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimDocumentTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimDocumentTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimDocumentTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimDocumentType(123);
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
        const entity = new ClaimDocumentType();
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
