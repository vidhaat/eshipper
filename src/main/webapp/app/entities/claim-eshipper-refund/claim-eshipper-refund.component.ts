import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';
import { ClaimEshipperRefundService } from './claim-eshipper-refund.service';
import { ClaimEshipperRefundDeleteDialogComponent } from './claim-eshipper-refund-delete-dialog.component';

@Component({
  selector: 'jhi-claim-eshipper-refund',
  templateUrl: './claim-eshipper-refund.component.html'
})
export class ClaimEshipperRefundComponent implements OnInit, OnDestroy {
  claimEshipperRefunds?: IClaimEshipperRefund[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimEshipperRefundService: ClaimEshipperRefundService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimEshipperRefundService.query().subscribe((res: HttpResponse<IClaimEshipperRefund[]>) => {
      this.claimEshipperRefunds = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimEshipperRefunds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimEshipperRefund): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimEshipperRefunds(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimEshipperRefundListModification', () => this.loadAll());
  }

  delete(claimEshipperRefund: IClaimEshipperRefund): void {
    const modalRef = this.modalService.open(ClaimEshipperRefundDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimEshipperRefund = claimEshipperRefund;
  }
}
