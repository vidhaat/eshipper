import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';
import { ClaimEshipperRefundService } from './claim-eshipper-refund.service';

@Component({
  templateUrl: './claim-eshipper-refund-delete-dialog.component.html'
})
export class ClaimEshipperRefundDeleteDialogComponent {
  claimEshipperRefund?: IClaimEshipperRefund;

  constructor(
    protected claimEshipperRefundService: ClaimEshipperRefundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimEshipperRefundService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimEshipperRefundListModification');
      this.activeModal.close();
    });
  }
}
