import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimComment } from 'app/shared/model/claim-comment.model';
import { ClaimCommentService } from './claim-comment.service';

@Component({
  templateUrl: './claim-comment-delete-dialog.component.html'
})
export class ClaimCommentDeleteDialogComponent {
  claimComment?: IClaimComment;

  constructor(
    protected claimCommentService: ClaimCommentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimCommentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimCommentListModification');
      this.activeModal.close();
    });
  }
}
