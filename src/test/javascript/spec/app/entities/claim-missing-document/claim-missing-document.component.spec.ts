import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimMissingDocumentComponent } from 'app/entities/claim-missing-document/claim-missing-document.component';
import { ClaimMissingDocumentService } from 'app/entities/claim-missing-document/claim-missing-document.service';
import { ClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';

describe('Component Tests', () => {
  describe('ClaimMissingDocument Management Component', () => {
    let comp: ClaimMissingDocumentComponent;
    let fixture: ComponentFixture<ClaimMissingDocumentComponent>;
    let service: ClaimMissingDocumentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimMissingDocumentComponent],
        providers: []
      })
        .overrideTemplate(ClaimMissingDocumentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimMissingDocumentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimMissingDocumentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimMissingDocument(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimMissingDocuments && comp.claimMissingDocuments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
