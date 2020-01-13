import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimDocumentType } from 'app/shared/model/claim-document-type.model';
import { ClaimDocumentTypeService } from './claim-document-type.service';

@Component({
  templateUrl: './claim-document-type-delete-dialog.component.html'
})
export class ClaimDocumentTypeDeleteDialogComponent {
  claimDocumentType?: IClaimDocumentType;

  constructor(
    protected claimDocumentTypeService: ClaimDocumentTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimDocumentTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimDocumentTypeListModification');
      this.activeModal.close();
    });
  }
}
