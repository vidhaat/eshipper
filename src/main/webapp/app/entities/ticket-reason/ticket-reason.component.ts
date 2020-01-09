import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITicketReason } from 'app/shared/model/ticket-reason.model';
import { TicketReasonService } from './ticket-reason.service';
import { TicketReasonDeleteDialogComponent } from './ticket-reason-delete-dialog.component';

@Component({
  selector: 'jhi-ticket-reason',
  templateUrl: './ticket-reason.component.html'
})
export class TicketReasonComponent implements OnInit, OnDestroy {
  ticketReasons?: ITicketReason[];
  eventSubscriber?: Subscription;

  constructor(
    protected ticketReasonService: TicketReasonService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ticketReasonService.query().subscribe((res: HttpResponse<ITicketReason[]>) => {
      this.ticketReasons = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTicketReasons();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITicketReason): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTicketReasons(): void {
    this.eventSubscriber = this.eventManager.subscribe('ticketReasonListModification', () => this.loadAll());
  }

  delete(ticketReason: ITicketReason): void {
    const modalRef = this.modalService.open(TicketReasonDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ticketReason = ticketReason;
  }
}
