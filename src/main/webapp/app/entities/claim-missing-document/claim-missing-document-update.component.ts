import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaimMissingDocument, ClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { ClaimMissingDocumentService } from './claim-missing-document.service';

@Component({
  selector: 'jhi-claim-missing-document-update',
  templateUrl: './claim-missing-document-update.component.html'
})
export class ClaimMissingDocumentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    documentName: [],
    notifyClient: [],
    uploaded: []
  });

  constructor(
    protected claimMissingDocumentService: ClaimMissingDocumentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimMissingDocument }) => {
      this.updateForm(claimMissingDocument);
    });
  }

  updateForm(claimMissingDocument: IClaimMissingDocument): void {
    this.editForm.patchValue({
      id: claimMissingDocument.id,
      documentName: claimMissingDocument.documentName,
      notifyClient: claimMissingDocument.notifyClient,
      uploaded: claimMissingDocument.uploaded
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimMissingDocument = this.createFromForm();
    if (claimMissingDocument.id !== undefined) {
      this.subscribeToSaveResponse(this.claimMissingDocumentService.update(claimMissingDocument));
    } else {
      this.subscribeToSaveResponse(this.claimMissingDocumentService.create(claimMissingDocument));
    }
  }

  private createFromForm(): IClaimMissingDocument {
    return {
      ...new ClaimMissingDocument(),
      id: this.editForm.get(['id'])!.value,
      documentName: this.editForm.get(['documentName'])!.value,
      notifyClient: this.editForm.get(['notifyClient'])!.value,
      uploaded: this.editForm.get(['uploaded'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimMissingDocument>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
