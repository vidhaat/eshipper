import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICurrency, Currency } from 'app/shared/model/currency.model';
import { CurrencyService } from './currency.service';
import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from 'app/entities/claim-carrier-refund/claim-carrier-refund.service';
import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';
import { ClaimEshipperRefundService } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund.service';

type SelectableEntity = IClaimCarrierRefund | IClaimEshipperRefund;

@Component({
  selector: 'jhi-currency-update',
  templateUrl: './currency-update.component.html'
})
export class CurrencyUpdateComponent implements OnInit {
  isSaving = false;

  claimcarrierrefunds: IClaimCarrierRefund[] = [];

  claimeshipperrefunds: IClaimEshipperRefund[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    claimCarrierRefundId: [],
    claimEshipperRefundId: []
  });

  constructor(
    protected currencyService: CurrencyService,
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    protected claimEshipperRefundService: ClaimEshipperRefundService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ currency }) => {
      this.updateForm(currency);

      this.claimCarrierRefundService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimCarrierRefund[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimCarrierRefund[]) => (this.claimcarrierrefunds = resBody));

      this.claimEshipperRefundService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimEshipperRefund[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimEshipperRefund[]) => (this.claimeshipperrefunds = resBody));
    });
  }

  updateForm(currency: ICurrency): void {
    this.editForm.patchValue({
      id: currency.id,
      name: currency.name,
      claimCarrierRefundId: currency.claimCarrierRefundId,
      claimEshipperRefundId: currency.claimEshipperRefundId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const currency = this.createFromForm();
    if (currency.id !== undefined) {
      this.subscribeToSaveResponse(this.currencyService.update(currency));
    } else {
      this.subscribeToSaveResponse(this.currencyService.create(currency));
    }
  }

  private createFromForm(): ICurrency {
    return {
      ...new Currency(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      claimCarrierRefundId: this.editForm.get(['claimCarrierRefundId'])!.value,
      claimEshipperRefundId: this.editForm.get(['claimEshipperRefundId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurrency>>): void {
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
