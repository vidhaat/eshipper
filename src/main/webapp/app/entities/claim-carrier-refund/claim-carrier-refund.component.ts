import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from './claim-carrier-refund.service';
import { ClaimCarrierRefundDeleteDialogComponent } from './claim-carrier-refund-delete-dialog.component';

@Component({
  selector: 'jhi-claim-carrier-refund',
  templateUrl: './claim-carrier-refund.component.html'
})
export class ClaimCarrierRefundComponent implements OnInit, OnDestroy {
  claimCarrierRefunds?: IClaimCarrierRefund[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimCarrierRefundService.query().subscribe((res: HttpResponse<IClaimCarrierRefund[]>) => {
      this.claimCarrierRefunds = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimCarrierRefunds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimCarrierRefund): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimCarrierRefunds(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimCarrierRefundListModification', () => this.loadAll());
  }

  delete(claimCarrierRefund: IClaimCarrierRefund): void {
    const modalRef = this.modalService.open(ClaimCarrierRefundDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimCarrierRefund = claimCarrierRefund;
  }
}
