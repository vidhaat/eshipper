import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimDocumentTypeComponent } from 'app/entities/claim-document-type/claim-document-type.component';
import { ClaimDocumentTypeService } from 'app/entities/claim-document-type/claim-document-type.service';
import { ClaimDocumentType } from 'app/shared/model/claim-document-type.model';

describe('Component Tests', () => {
  describe('ClaimDocumentType Management Component', () => {
    let comp: ClaimDocumentTypeComponent;
    let fixture: ComponentFixture<ClaimDocumentTypeComponent>;
    let service: ClaimDocumentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimDocumentTypeComponent],
        providers: []
      })
        .overrideTemplate(ClaimDocumentTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimDocumentTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimDocumentTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimDocumentType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimDocumentTypes && comp.claimDocumentTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
