import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimAttachment } from 'app/shared/model/claim-attachment.model';

@Component({
  selector: 'jhi-claim-attachment-detail',
  templateUrl: './claim-attachment-detail.component.html'
})
export class ClaimAttachmentDetailComponent implements OnInit {
  claimAttachment: IClaimAttachment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimAttachment }) => {
      this.claimAttachment = claimAttachment;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
