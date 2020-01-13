import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IClaimCarrierRefund, ClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from './claim-carrier-refund.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { IClaimCarrierRefundStatus } from 'app/shared/model/claim-carrier-refund-status.model';
import { ClaimCarrierRefundStatusService } from 'app/entities/claim-carrier-refund-status/claim-carrier-refund-status.service';

type SelectableEntity = ICurrency | IClaimCarrierRefundStatus;

@Component({
  selector: 'jhi-claim-carrier-refund-update',
  templateUrl: './claim-carrier-refund-update.component.html'
})
export class ClaimCarrierRefundUpdateComponent implements OnInit {
  isSaving = false;

  currencies: ICurrency[] = [];

  claimcarrierrefundstatuses: IClaimCarrierRefundStatus[] = [];

  editForm = this.fb.group({
    id: [],
    carrierClaim: [],
    amount: [],
    chequeNumber: [],
    cheque: [],
    date: [],
    currencyId: [],
    refundStatusId: []
  });

  constructor(
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    protected currencyService: CurrencyService,
    protected claimCarrierRefundStatusService: ClaimCarrierRefundStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimCarrierRefund }) => {
      this.updateForm(claimCarrierRefund);

      this.currencyService
        .query()
        .pipe(
          map((res: HttpResponse<ICurrency[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICurrency[]) => (this.currencies = resBody));

      this.claimCarrierRefundStatusService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimCarrierRefundStatus[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimCarrierRefundStatus[]) => (this.claimcarrierrefundstatuses = resBody));
    });
  }

  updateForm(claimCarrierRefund: IClaimCarrierRefund): void {
    this.editForm.patchValue({
      id: claimCarrierRefund.id,
      carrierClaim: claimCarrierRefund.carrierClaim,
      amount: claimCarrierRefund.amount,
      chequeNumber: claimCarrierRefund.chequeNumber,
      cheque: claimCarrierRefund.cheque,
      date: claimCarrierRefund.date != null ? claimCarrierRefund.date.format(DATE_TIME_FORMAT) : null,
      currencyId: claimCarrierRefund.currencyId,
      refundStatusId: claimCarrierRefund.refundStatusId
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
      date: this.editForm.get(['date'])!.value != null ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      currencyId: this.editForm.get(['currencyId'])!.value,
      refundStatusId: this.editForm.get(['refundStatusId'])!.value
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
