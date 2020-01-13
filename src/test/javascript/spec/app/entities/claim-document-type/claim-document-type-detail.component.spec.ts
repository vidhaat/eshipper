import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimDocumentTypeDetailComponent } from 'app/entities/claim-document-type/claim-document-type-detail.component';
import { ClaimDocumentType } from 'app/shared/model/claim-document-type.model';

describe('Component Tests', () => {
  describe('ClaimDocumentType Management Detail Component', () => {
    let comp: ClaimDocumentTypeDetailComponent;
    let fixture: ComponentFixture<ClaimDocumentTypeDetailComponent>;
    const route = ({ data: of({ claimDocumentType: new ClaimDocumentType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimDocumentTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimDocumentTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimDocumentTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimDocumentType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimDocumentType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
