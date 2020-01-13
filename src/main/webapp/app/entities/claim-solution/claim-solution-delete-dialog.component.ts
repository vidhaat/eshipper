import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClaimSolution } from 'app/shared/model/claim-solution.model';
import { ClaimSolutionService } from './claim-solution.service';

@Component({
  templateUrl: './claim-solution-delete-dialog.component.html'
})
export class ClaimSolutionDeleteDialogComponent {
  claimSolution?: IClaimSolution;

  constructor(
    protected claimSolutionService: ClaimSolutionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.claimSolutionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('claimSolutionListModification');
      this.activeModal.close();
    });
  }
}
