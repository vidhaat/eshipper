import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaimStatus, ClaimStatus } from 'app/shared/model/claim-status.model';
import { ClaimStatusService } from './claim-status.service';

@Component({
  selector: 'jhi-claim-status-update',
  templateUrl: './claim-status-update.component.html'
})
export class ClaimStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: []
  });

  constructor(protected claimStatusService: ClaimStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimStatus }) => {
      this.updateForm(claimStatus);
    });
  }

  updateForm(claimStatus: IClaimStatus): void {
    this.editForm.patchValue({
      id: claimStatus.id,
      name: claimStatus.name
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimStatus = this.createFromForm();
    if (claimStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.claimStatusService.update(claimStatus));
    } else {
      this.subscribeToSaveResponse(this.claimStatusService.create(claimStatus));
    }
  }

  private createFromForm(): IClaimStatus {
    return {
      ...new ClaimStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimStatus>>): void {
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
