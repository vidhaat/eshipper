import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimDocumentType } from 'app/shared/model/claim-document-type.model';

@Component({
  selector: 'jhi-claim-document-type-detail',
  templateUrl: './claim-document-type-detail.component.html'
})
export class ClaimDocumentTypeDetailComponent implements OnInit {
  claimDocumentType: IClaimDocumentType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimDocumentType }) => {
      this.claimDocumentType = claimDocumentType;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
