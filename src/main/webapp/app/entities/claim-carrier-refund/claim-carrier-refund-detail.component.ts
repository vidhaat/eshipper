import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';

@Component({
  selector: 'jhi-claim-carrier-refund-detail',
  templateUrl: './claim-carrier-refund-detail.component.html'
})
export class ClaimCarrierRefundDetailComponent implements OnInit {
  claimCarrierRefund: IClaimCarrierRefund | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimCarrierRefund }) => {
      this.claimCarrierRefund = claimCarrierRefund;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
