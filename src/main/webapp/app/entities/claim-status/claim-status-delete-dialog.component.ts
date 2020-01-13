import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimStatus } from 'app/shared/model/claim-status.model';
import { ClaimStatusService } from './claim-status.service';

@Component({
  templateUrl: './claim-status-delete-dialog.component.html'
})
export class ClaimStatusDeleteDialogComponent {
  claimStatus?: IClaimStatus;

  constructor(
    protected claimStatusService: ClaimStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimStatusListModification');
      this.activeModal.close();
    });
  }
}
