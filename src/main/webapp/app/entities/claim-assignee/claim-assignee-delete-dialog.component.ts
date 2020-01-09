import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { ClaimAssigneeService } from './claim-assignee.service';

@Component({
  templateUrl: './claim-assignee-delete-dialog.component.html'
})
export class ClaimAssigneeDeleteDialogComponent {
  claimAssignee?: IClaimAssignee;

  constructor(
    protected claimAssigneeService: ClaimAssigneeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimAssigneeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimAssigneeListModification');
      this.activeModal.close();
    });
  }
}
