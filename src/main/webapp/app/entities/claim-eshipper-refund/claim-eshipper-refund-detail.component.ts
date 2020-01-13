import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';

@Component({
  selector: 'jhi-claim-eshipper-refund-detail',
  templateUrl: './claim-eshipper-refund-detail.component.html'
})
export class ClaimEshipperRefundDetailComponent implements OnInit {
  claimEshipperRefund: IClaimEshipperRefund | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimEshipperRefund }) => {
      this.claimEshipperRefund = claimEshipperRefund;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
