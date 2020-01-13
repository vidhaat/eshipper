import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';
import { ElasticShippingClaimService } from './elastic-shipping-claim.service';

@Component({
  templateUrl: './elastic-shipping-claim-delete-dialog.component.html'
})
export class ElasticShippingClaimDeleteDialogComponent {
  elasticShippingClaim?: IElasticShippingClaim;

  constructor(
    protected elasticShippingClaimService: ElasticShippingClaimService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.elasticShippingClaimService.delete(id).subscribe(() => {
      this.eventManager.broadcast('elasticShippingClaimListModification');
      this.activeModal.close();
    });
  }
}
