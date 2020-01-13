import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimSolutionComponent } from 'app/entities/claim-solution/claim-solution.component';
import { ClaimSolutionService } from 'app/entities/claim-solution/claim-solution.service';
import { ClaimSolution } from 'app/shared/model/claim-solution.model';

describe('Component Tests', () => {
  describe('ClaimSolution Management Component', () => {
    let comp: ClaimSolutionComponent;
    let fixture: ComponentFixture<ClaimSolutionComponent>;
    let service: ClaimSolutionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimSolutionComponent],
        providers: []
      })
        .overrideTemplate(ClaimSolutionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimSolutionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimSolutionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimSolution(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimSolutions && comp.claimSolutions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
