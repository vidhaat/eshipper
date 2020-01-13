import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ContactPreferenceComponent } from 'app/entities/contact-preference/contact-preference.component';
import { ContactPreferenceService } from 'app/entities/contact-preference/contact-preference.service';
import { ContactPreference } from 'app/shared/model/contact-preference.model';

describe('Component Tests', () => {
  describe('ContactPreference Management Component', () => {
    let comp: ContactPreferenceComponent;
    let fixture: ComponentFixture<ContactPreferenceComponent>;
    let service: ContactPreferenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ContactPreferenceComponent],
        providers: []
      })
        .overrideTemplate(ContactPreferenceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactPreferenceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactPreferenceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContactPreference(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contactPreferences && comp.contactPreferences[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
