import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { ClaimMissingDocumentService } from './claim-missing-document.service';

@Component({
  templateUrl: './claim-missing-document-delete-dialog.component.html'
})
export class ClaimMissingDocumentDeleteDialogComponent {
  claimMissingDocument?: IClaimMissingDocument;

  constructor(
    protected claimMissingDocumentService: ClaimMissingDocumentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimMissingDocumentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimMissingDocumentListModification');
      this.activeModal.close();
    });
  }
}
