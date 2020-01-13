import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ContactPreferenceDetailComponent } from 'app/entities/contact-preference/contact-preference-detail.component';
import { ContactPreference } from 'app/shared/model/contact-preference.model';

describe('Component Tests', () => {
  describe('ContactPreference Management Detail Component', () => {
    let comp: ContactPreferenceDetailComponent;
    let fixture: ComponentFixture<ContactPreferenceDetailComponent>;
    const route = ({ data: of({ contactPreference: new ContactPreference(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ContactPreferenceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ContactPreferenceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContactPreferenceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load contactPreference on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contactPreference).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
