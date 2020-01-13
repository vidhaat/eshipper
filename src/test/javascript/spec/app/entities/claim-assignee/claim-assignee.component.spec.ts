import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimAssigneeComponent } from 'app/entities/claim-assignee/claim-assignee.component';
import { ClaimAssigneeService } from 'app/entities/claim-assignee/claim-assignee.service';
import { ClaimAssignee } from 'app/shared/model/claim-assignee.model';

describe('Component Tests', () => {
  describe('ClaimAssignee Management Component', () => {
    let comp: ClaimAssigneeComponent;
    let fixture: ComponentFixture<ClaimAssigneeComponent>;
    let service: ClaimAssigneeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimAssigneeComponent],
        providers: []
      })
        .overrideTemplate(ClaimAssigneeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimAssigneeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimAssigneeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimAssignee(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimAssignees && comp.claimAssignees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
