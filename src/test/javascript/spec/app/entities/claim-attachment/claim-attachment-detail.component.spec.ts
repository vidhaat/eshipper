import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimAttachmentDetailComponent } from 'app/entities/claim-attachment/claim-attachment-detail.component';
import { ClaimAttachment } from 'app/shared/model/claim-attachment.model';

describe('Component Tests', () => {
  describe('ClaimAttachment Management Detail Component', () => {
    let comp: ClaimAttachmentDetailComponent;
    let fixture: ComponentFixture<ClaimAttachmentDetailComponent>;
    const route = ({ data: of({ claimAttachment: new ClaimAttachment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimAttachmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimAttachmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimAttachmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimAttachment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimAttachment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
