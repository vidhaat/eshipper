import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITicketReason } from 'app/shared/model/ticket-reason.model';
import { TicketReasonService } from './ticket-reason.service';

@Component({
  templateUrl: './ticket-reason-delete-dialog.component.html'
})
export class TicketReasonDeleteDialogComponent {
  ticketReason?: ITicketReason;

  constructor(
    protected ticketReasonService: TicketReasonService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ticketReasonService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ticketReasonListModification');
      this.activeModal.close();
    });
  }
}
