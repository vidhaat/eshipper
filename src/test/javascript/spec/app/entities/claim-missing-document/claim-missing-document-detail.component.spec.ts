import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimMissingDocumentDetailComponent } from 'app/entities/claim-missing-document/claim-missing-document-detail.component';
import { ClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';

describe('Component Tests', () => {
  describe('ClaimMissingDocument Management Detail Component', () => {
    let comp: ClaimMissingDocumentDetailComponent;
    let fixture: ComponentFixture<ClaimMissingDocumentDetailComponent>;
    const route = ({ data: of({ claimMissingDocument: new ClaimMissingDocument(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimMissingDocumentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimMissingDocumentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimMissingDocumentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimMissingDocument on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimMissingDocument).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
