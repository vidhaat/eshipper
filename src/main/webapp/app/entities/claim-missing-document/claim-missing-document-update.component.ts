import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClaimMissingDocument, ClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { ClaimMissingDocumentService } from './claim-missing-document.service';
import { IClaimDocumentType } from 'app/shared/model/claim-document-type.model';
import { ClaimDocumentTypeService } from 'app/entities/claim-document-type/claim-document-type.service';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';

type SelectableEntity = IClaimDocumentType | IShippingClaim;

@Component({
  selector: 'jhi-claim-missing-document-update',
  templateUrl: './claim-missing-document-update.component.html'
})
export class ClaimMissingDocumentUpdateComponent implements OnInit {
  isSaving = false;

  claimdocumenttypes: IClaimDocumentType[] = [];

  shippingclaims: IShippingClaim[] = [];

  editForm = this.fb.group({
    id: [],
    documentName: [],
    notifyClient: [],
    uploaded: [],
    claimDocumentTypeId: [],
    shippingClaimId: []
  });

  constructor(
    protected claimMissingDocumentService: ClaimMissingDocumentService,
    protected claimDocumentTypeService: ClaimDocumentTypeService,
    protected shippingClaimService: ShippingClaimService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimMissingDocument }) => {
      this.updateForm(claimMissingDocument);

      this.claimDocumentTypeService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimDocumentType[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimDocumentType[]) => (this.claimdocumenttypes = resBody));

      this.shippingClaimService
        .query()
        .pipe(
          map((res: HttpResponse<IShippingClaim[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IShippingClaim[]) => (this.shippingclaims = resBody));
    });
  }

  updateForm(claimMissingDocument: IClaimMissingDocument): void {
    this.editForm.patchValue({
      id: claimMissingDocument.id,
      documentName: claimMissingDocument.documentName,
      notifyClient: claimMissingDocument.notifyClient,
      uploaded: claimMissingDocument.uploaded,
      claimDocumentTypeId: claimMissingDocument.claimDocumentTypeId,
      shippingClaimId: claimMissingDocument.shippingClaimId
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
      uploaded: this.editForm.get(['uploaded'])!.value,
      claimDocumentTypeId: this.editForm.get(['claimDocumentTypeId'])!.value,
      shippingClaimId: this.editForm.get(['shippingClaimId'])!.value
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
