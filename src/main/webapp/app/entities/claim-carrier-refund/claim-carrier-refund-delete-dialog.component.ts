import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from './claim-carrier-refund.service';

@Component({
  templateUrl: './claim-carrier-refund-delete-dialog.component.html'
})
export class ClaimCarrierRefundDeleteDialogComponent {
  claimCarrierRefund?: IClaimCarrierRefund;

  constructor(
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimCarrierRefundService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimCarrierRefundListModification');
      this.activeModal.close();
    });
  }
}
