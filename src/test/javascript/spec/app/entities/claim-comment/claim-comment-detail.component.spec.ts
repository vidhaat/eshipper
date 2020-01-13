import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimCommentDetailComponent } from 'app/entities/claim-comment/claim-comment-detail.component';
import { ClaimComment } from 'app/shared/model/claim-comment.model';

describe('Component Tests', () => {
  describe('ClaimComment Management Detail Component', () => {
    let comp: ClaimCommentDetailComponent;
    let fixture: ComponentFixture<ClaimCommentDetailComponent>;
    const route = ({ data: of({ claimComment: new ClaimComment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimCommentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimCommentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimCommentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimComment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimComment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
