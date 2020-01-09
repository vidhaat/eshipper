import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimAttachment } from 'app/shared/model/claim-attachment.model';
import { ClaimAttachmentService } from './claim-attachment.service';

@Component({
  templateUrl: './claim-attachment-delete-dialog.component.html'
})
export class ClaimAttachmentDeleteDialogComponent {
  claimAttachment?: IClaimAttachment;

  constructor(
    protected claimAttachmentService: ClaimAttachmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimAttachmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimAttachmentListModification');
      this.activeModal.close();
    });
  }
}
