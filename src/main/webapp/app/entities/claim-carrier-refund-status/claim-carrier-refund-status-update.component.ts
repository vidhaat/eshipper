import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClaimCarrierRefundStatus, ClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';
import { ClaimCarrierRefundStatusService } from './claim-carrier-refund-status.service';
import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from 'app/entities/claim-carrier-refund/claim-carrier-refund.service';

@Component({
  selector: 'jhi-claim-carrier-refund-status-update',
  templateUrl: './claim-carrier-refund-status-update.component.html'
})
export class ClaimCarrierRefundStatusUpdateComponent implements OnInit {
  isSaving = false;

  claimcarrierrefunds: IClaimCarrierRefund[] = [];

  editForm = this.fb.group({
    id: [],
    status: [],
    claimCarrierRefundId: []
  });

  constructor(
    protected claimCarrierRefundStatusService: ClaimCarrierRefundStatusService,
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimCarrierRefundStatus }) => {
      this.updateForm(claimCarrierRefundStatus);

      this.claimCarrierRefundService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimCarrierRefund[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimCarrierRefund[]) => (this.claimcarrierrefunds = resBody));
    });
  }

  updateForm(claimCarrierRefundStatus: IClaimCarrierRefundStatus): void {
    this.editForm.patchValue({
      id: claimCarrierRefundStatus.id,
      status: claimCarrierRefundStatus.status,
      claimCarrierRefundId: claimCarrierRefundStatus.claimCarrierRefundId
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
      status: this.editForm.get(['status'])!.value,
      claimCarrierRefundId: this.editForm.get(['claimCarrierRefundId'])!.value
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

  trackById(index: number, item: IClaimCarrierRefund): any {
    return item.id;
  }
}
