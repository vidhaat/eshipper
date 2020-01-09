import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';
import { ClaimCarrierRefundStatusService } from './claim-carrier-refund-status.service';
import { ClaimCarrierRefundStatusDeleteDialogComponent } from './claim-carrier-refund-status-delete-dialog.component';

@Component({
  selector: 'jhi-claim-carrier-refund-status',
  templateUrl: './claim-carrier-refund-status.component.html'
})
export class ClaimCarrierRefundStatusComponent implements OnInit, OnDestroy {
  claimCarrierRefundStatuses?: IClaimCarrierRefundStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimCarrierRefundStatusService: ClaimCarrierRefundStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimCarrierRefundStatusService.query().subscribe((res: HttpResponse<IClaimCarrierRefundStatus[]>) => {
      this.claimCarrierRefundStatuses = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimCarrierRefundStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimCarrierRefundStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimCarrierRefundStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimCarrierRefundStatusListModification', () => this.loadAll());
  }

  delete(claimCarrierRefundStatus: IClaimCarrierRefundStatus): void {
    const modalRef = this.modalService.open(ClaimCarrierRefundStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimCarrierRefundStatus = claimCarrierRefundStatus;
  }
}
