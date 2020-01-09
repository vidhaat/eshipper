import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { TicketReasonDetailComponent } from 'app/entities/ticket-reason/ticket-reason-detail.component';
import { TicketReason } from 'app/shared/model/ticket-reason.model';

describe('Component Tests', () => {
  describe('TicketReason Management Detail Component', () => {
    let comp: TicketReasonDetailComponent;
    let fixture: ComponentFixture<TicketReasonDetailComponent>;
    const route = ({ data: of({ ticketReason: new TicketReason(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [TicketReasonDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TicketReasonDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TicketReasonDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ticketReason on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ticketReason).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
