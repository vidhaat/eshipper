import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITicketReason } from 'app/shared/model/ticket-reason.model';

@Component({
  selector: 'jhi-ticket-reason-detail',
  templateUrl: './ticket-reason-detail.component.html'
})
export class TicketReasonDetailComponent implements OnInit {
  ticketReason: ITicketReason | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ticketReason }) => {
      this.ticketReason = ticketReason;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
