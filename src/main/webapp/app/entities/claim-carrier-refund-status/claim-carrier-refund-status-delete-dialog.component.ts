import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';
import { ClaimCarrierRefundStatusService } from './claim-carrier-refund-status.service';

@Component({
  templateUrl: './claim-carrier-refund-status-delete-dialog.component.html'
})
export class ClaimCarrierRefundStatusDeleteDialogComponent {
  claimCarrierRefundStatus?: IClaimCarrierRefundStatus;

  constructor(
    protected claimCarrierRefundStatusService: ClaimCarrierRefundStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimCarrierRefundStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimCarrierRefundStatusListModification');
      this.activeModal.close();
    });
  }
}
