import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IClaimEshipperRefund, ClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';
import { ClaimEshipperRefundService } from './claim-eshipper-refund.service';

@Component({
  selector: 'jhi-claim-eshipper-refund-update',
  templateUrl: './claim-eshipper-refund-update.component.html'
})
export class ClaimEshipperRefundUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    amount: [],
    cheque: [],
    date: []
  });

  constructor(
    protected claimEshipperRefundService: ClaimEshipperRefundService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimEshipperRefund }) => {
      this.updateForm(claimEshipperRefund);
    });
  }

  updateForm(claimEshipperRefund: IClaimEshipperRefund): void {
    this.editForm.patchValue({
      id: claimEshipperRefund.id,
      amount: claimEshipperRefund.amount,
      cheque: claimEshipperRefund.cheque,
      date: claimEshipperRefund.date != null ? claimEshipperRefund.date.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimEshipperRefund = this.createFromForm();
    if (claimEshipperRefund.id !== undefined) {
      this.subscribeToSaveResponse(this.claimEshipperRefundService.update(claimEshipperRefund));
    } else {
      this.subscribeToSaveResponse(this.claimEshipperRefundService.create(claimEshipperRefund));
    }
  }

  private createFromForm(): IClaimEshipperRefund {
    return {
      ...new ClaimEshipperRefund(),
      id: this.editForm.get(['id'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      cheque: this.editForm.get(['cheque'])!.value,
      date: this.editForm.get(['date'])!.value != null ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimEshipperRefund>>): void {
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
