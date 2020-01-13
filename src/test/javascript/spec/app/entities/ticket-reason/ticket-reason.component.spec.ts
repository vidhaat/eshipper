import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { TicketReasonComponent } from 'app/entities/ticket-reason/ticket-reason.component';
import { TicketReasonService } from 'app/entities/ticket-reason/ticket-reason.service';
import { TicketReason } from 'app/shared/model/ticket-reason.model';

describe('Component Tests', () => {
  describe('TicketReason Management Component', () => {
    let comp: TicketReasonComponent;
    let fixture: ComponentFixture<TicketReasonComponent>;
    let service: TicketReasonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [TicketReasonComponent],
        providers: []
      })
        .overrideTemplate(TicketReasonComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TicketReasonComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TicketReasonService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TicketReason(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ticketReasons && comp.ticketReasons[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
