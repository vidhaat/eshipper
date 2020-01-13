import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimAssigneeDetailComponent } from 'app/entities/claim-assignee/claim-assignee-detail.component';
import { ClaimAssignee } from 'app/shared/model/claim-assignee.model';

describe('Component Tests', () => {
  describe('ClaimAssignee Management Detail Component', () => {
    let comp: ClaimAssigneeDetailComponent;
    let fixture: ComponentFixture<ClaimAssigneeDetailComponent>;
    const route = ({ data: of({ claimAssignee: new ClaimAssignee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimAssigneeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimAssigneeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimAssigneeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimAssignee on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimAssignee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
