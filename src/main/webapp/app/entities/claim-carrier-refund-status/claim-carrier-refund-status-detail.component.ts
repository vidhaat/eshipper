import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';

@Component({
  selector: 'jhi-claim-carrier-refund-status-detail',
  templateUrl: './claim-carrier-refund-status-detail.component.html'
})
export class ClaimCarrierRefundStatusDetailComponent implements OnInit {
  claimCarrierRefundStatus: IClaimCarrierRefundStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimCarrierRefundStatus }) => {
      this.claimCarrierRefundStatus = claimCarrierRefundStatus;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
