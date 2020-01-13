import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimMissingDocumentUpdateComponent } from 'app/entities/claim-missing-document/claim-missing-document-update.component';
import { ClaimMissingDocumentService } from 'app/entities/claim-missing-document/claim-missing-document.service';
import { ClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';

describe('Component Tests', () => {
  describe('ClaimMissingDocument Management Update Component', () => {
    let comp: ClaimMissingDocumentUpdateComponent;
    let fixture: ComponentFixture<ClaimMissingDocumentUpdateComponent>;
    let service: ClaimMissingDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimMissingDocumentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClaimMissingDocumentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimMissingDocumentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimMissingDocumentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClaimMissingDocument(123);
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
        const entity = new ClaimMissingDocument();
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
