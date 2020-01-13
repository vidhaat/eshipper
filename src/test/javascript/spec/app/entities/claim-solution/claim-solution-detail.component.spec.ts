import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ClaimSolutionDetailComponent } from 'app/entities/claim-solution/claim-solution-detail.component';
import { ClaimSolution } from 'app/shared/model/claim-solution.model';

describe('Component Tests', () => {
  describe('ClaimSolution Management Detail Component', () => {
    let comp: ClaimSolutionDetailComponent;
    let fixture: ComponentFixture<ClaimSolutionDetailComponent>;
    const route = ({ data: of({ claimSolution: new ClaimSolution(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimSolutionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClaimSolutionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClaimSolutionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load claimSolution on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.claimSolution).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
