import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IClaimCarrierRefund, ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from './claim-carrier-refund.service';

@Component({
  selector: 'jhi-claim-carrier-refund-update',
  templateUrl: './claim-carrier-refund-update.component.html'
})
export class ClaimCarrierRefundUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    carrierClaim: [],
    amount: [],
    chequeNumber: [],
    cheque: [],
    date: []
  });

  constructor(
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimCarrierRefund }) => {
      this.updateForm(claimCarrierRefund);
    });
  }

  updateForm(claimCarrierRefund: IClaimCarrierRefund): void {
    this.editForm.patchValue({
      id: claimCarrierRefund.id,
      carrierClaim: claimCarrierRefund.carrierClaim,
      amount: claimCarrierRefund.amount,
      chequeNumber: claimCarrierRefund.chequeNumber,
      cheque: claimCarrierRefund.cheque,
      date: claimCarrierRefund.date != null ? claimCarrierRefund.date.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimCarrierRefund = this.createFromForm();
    if (claimCarrierRefund.id !== undefined) {
      this.subscribeToSaveResponse(this.claimCarrierRefundService.update(claimCarrierRefund));
    } else {
      this.subscribeToSaveResponse(this.claimCarrierRefundService.create(claimCarrierRefund));
    }
  }

  private createFromForm(): IClaimCarrierRefund {
    return {
      ...new ClaimCarrierRefund(),
      id: this.editForm.get(['id'])!.value,
      carrierClaim: this.editForm.get(['carrierClaim'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      chequeNumber: this.editForm.get(['chequeNumber'])!.value,
      cheque: this.editForm.get(['cheque'])!.value,
      date: this.editForm.get(['date'])!.value != null ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimCarrierRefund>>): void {
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
