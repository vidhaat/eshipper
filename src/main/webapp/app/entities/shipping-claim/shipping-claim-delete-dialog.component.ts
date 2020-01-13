import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from './shipping-claim.service';

@Component({
  templateUrl: './shipping-claim-delete-dialog.component.html'
})
export class ShippingClaimDeleteDialogComponent {
  shippingClaim?: IShippingClaim;

  constructor(
    protected shippingClaimService: ShippingClaimService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shippingClaimService.delete(id).subscribe(() => {
      this.eventManager.broadcast('shippingClaimListModification');
      this.activeModal.close();
    });
  }
}
