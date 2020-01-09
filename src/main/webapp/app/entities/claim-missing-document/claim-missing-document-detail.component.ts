import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';

@Component({
  selector: 'jhi-claim-missing-document-detail',
  templateUrl: './claim-missing-document-detail.component.html'
})
export class ClaimMissingDocumentDetailComponent implements OnInit {
  claimMissingDocument: IClaimMissingDocument | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimMissingDocument }) => {
      this.claimMissingDocument = claimMissingDocument;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
