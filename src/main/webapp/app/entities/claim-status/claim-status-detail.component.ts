import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimStatus } from 'app/shared/model/claim-status.model';

@Component({
  selector: 'jhi-claim-status-detail',
  templateUrl: './claim-status-detail.component.html'
})
export class ClaimStatusDetailComponent implements OnInit {
  claimStatus: IClaimStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimStatus }) => {
      this.claimStatus = claimStatus;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
