import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimAssignee } from 'app/shared/model/claim-assignee.model';

@Component({
  selector: 'jhi-claim-assignee-detail',
  templateUrl: './claim-assignee-detail.component.html'
})
export class ClaimAssigneeDetailComponent implements OnInit {
  claimAssignee: IClaimAssignee | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimAssignee }) => {
      this.claimAssignee = claimAssignee;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
