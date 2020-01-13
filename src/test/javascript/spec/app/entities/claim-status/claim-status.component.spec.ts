import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ClaimStatusComponent } from 'app/entities/claim-status/claim-status.component';
import { ClaimStatusService } from 'app/entities/claim-status/claim-status.service';
import { ClaimStatus } from 'app/shared/model/claim-status.model';

describe('Component Tests', () => {
  describe('ClaimStatus Management Component', () => {
    let comp: ClaimStatusComponent;
    let fixture: ComponentFixture<ClaimStatusComponent>;
    let service: ClaimStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ClaimStatusComponent],
        providers: []
      })
        .overrideTemplate(ClaimStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClaimStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClaimStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ClaimStatus(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.claimStatuses && comp.claimStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
