import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaimCarrierRefundStatus, ClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';
import { ClaimCarrierRefundStatusService } from './claim-carrier-refund-status.service';

@Component({
  selector: 'jhi-claim-carrier-refund-status-update',
  templateUrl: './claim-carrier-refund-status-update.component.html'
})
export class ClaimCarrierRefundStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    status: []
  });

  constructor(
    protected claimCarrierRefundStatusService: ClaimCarrierRefundStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimCarrierRefundStatus }) => {
      this.updateForm(claimCarrierRefundStatus);
    });
  }

  updateForm(claimCarrierRefundStatus: IClaimCarrierRefundStatus): void {
    this.editForm.patchValue({
      id: claimCarrierRefundStatus.id,
      status: claimCarrierRefundStatus.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimCarrierRefundStatus = this.createFromForm();
    if (claimCarrierRefundStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.claimCarrierRefundStatusService.update(claimCarrierRefundStatus));
    } else {
      this.subscribeToSaveResponse(this.claimCarrierRefundStatusService.create(claimCarrierRefundStatus));
    }
  }

  private createFromForm(): IClaimCarrierRefundStatus {
    return {
      ...new ClaimCarrierRefundStatus(),
      id: this.editForm.get(['id'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimCarrierRefundStatus>>): void {
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
