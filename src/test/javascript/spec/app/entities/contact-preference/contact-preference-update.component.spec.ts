import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ContactPreferenceUpdateComponent } from 'app/entities/contact-preference/contact-preference-update.component';
import { ContactPreferenceService } from 'app/entities/contact-preference/contact-preference.service';
import { ContactPreference } from 'app/shared/model/contact-preference.model';

describe('Component Tests', () => {
  describe('ContactPreference Management Update Component', () => {
    let comp: ContactPreferenceUpdateComponent;
    let fixture: ComponentFixture<ContactPreferenceUpdateComponent>;
    let service: ContactPreferenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ContactPreferenceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ContactPreferenceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactPreferenceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactPreferenceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContactPreference(123);
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
        const entity = new ContactPreference();
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
