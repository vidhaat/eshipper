import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCommentComponent } from 'app/entities/claim-comment/claim-comment.component';
import { ClaimCommentService } from 'app/entities/claim-comment/claim-comment.service';
import { ClaimComment } from 'app/shared/model/claim-comment.model';

describe('Component Tests', () => {
  describe('ClaimComment Management Component', () => {
    let comp: ClaimCommentComponent;
    let fixture: ComponentFixture<ClaimCommentComponent>;
    let service: ClaimCommentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCommentComponent],
        providers: []
      })
        .overrideTemplate(ClaimCommentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimCommentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimCommentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimComment(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimComments && comp.claimComments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
