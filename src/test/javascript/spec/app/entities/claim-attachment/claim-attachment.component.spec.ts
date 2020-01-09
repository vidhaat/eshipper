import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimAttachmentComponent } from 'app/entities/claim-attachment/claim-attachment.component';
import { ClaimAttachmentService } from 'app/entities/claim-attachment/claim-attachment.service';
import { ClaimAttachment } from 'app/shared/model/claim-attachment.model';

describe('Component Tests', () => {
  describe('ClaimAttachment Management Component', () => {
    let comp: ClaimAttachmentComponent;
    let fixture: ComponentFixture<ClaimAttachmentComponent>;
    let service: ClaimAttachmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimAttachmentComponent],
        providers: []
      })
        .overrideTemplate(ClaimAttachmentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimAttachmentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimAttachmentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimAttachment(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimAttachments && comp.claimAttachments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
