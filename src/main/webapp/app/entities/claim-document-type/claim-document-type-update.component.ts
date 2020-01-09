import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClaimDocumentType, ClaimDocumentType } from 'app/shared/model/claim-document-type.model';
import { ClaimDocumentTypeService } from './claim-document-type.service';
import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { ClaimMissingDocumentService } from 'app/entities/claim-missing-document/claim-missing-document.service';

@Component({
  selector: 'jhi-claim-document-type-update',
  templateUrl: './claim-document-type-update.component.html'
})
export class ClaimDocumentTypeUpdateComponent implements OnInit {
  isSaving = false;

  claimmissingdocuments: IClaimMissingDocument[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    claimMissingDocumentId: []
  });

  constructor(
    protected claimDocumentTypeService: ClaimDocumentTypeService,
    protected claimMissingDocumentService: ClaimMissingDocumentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimDocumentType }) => {
      this.updateForm(claimDocumentType);

      this.claimMissingDocumentService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimMissingDocument[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimMissingDocument[]) => (this.claimmissingdocuments = resBody));
    });
  }

  updateForm(claimDocumentType: IClaimDocumentType): void {
    this.editForm.patchValue({
      id: claimDocumentType.id,
      name: claimDocumentType.name,
      claimMissingDocumentId: claimDocumentType.claimMissingDocumentId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimDocumentType = this.createFromForm();
    if (claimDocumentType.id !== undefined) {
      this.subscribeToSaveResponse(this.claimDocumentTypeService.update(claimDocumentType));
    } else {
      this.subscribeToSaveResponse(this.claimDocumentTypeService.create(claimDocumentType));
    }
  }

  private createFromForm(): IClaimDocumentType {
    return {
      ...new ClaimDocumentType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      claimMissingDocumentId: this.editForm.get(['claimMissingDocumentId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimDocumentType>>): void {
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

  trackById(index: number, item: IClaimMissingDocument): any {
    return item.id;
  }
}
