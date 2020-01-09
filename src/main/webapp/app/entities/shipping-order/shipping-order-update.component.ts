import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IShippingOrder, ShippingOrder } from 'app/shared/model/shipping-order.model';
import { ShippingOrderService } from './shipping-order.service';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';

@Component({
  selector: 'jhi-shipping-order-update',
  templateUrl: './shipping-order-update.component.html'
})
export class ShippingOrderUpdateComponent implements OnInit {
  isSaving = false;

  shippingclaims: IShippingClaim[] = [];

  editForm = this.fb.group({
    id: [],
    shippingClaimId: []
  });

  constructor(
    protected shippingOrderService: ShippingOrderService,
    protected shippingClaimService: ShippingClaimService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingOrder }) => {
      this.updateForm(shippingOrder);

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

  updateForm(shippingOrder: IShippingOrder): void {
    this.editForm.patchValue({
      id: shippingOrder.id,
      shippingClaimId: shippingOrder.shippingClaimId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shippingOrder = this.createFromForm();
    if (shippingOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.shippingOrderService.update(shippingOrder));
    } else {
      this.subscribeToSaveResponse(this.shippingOrderService.create(shippingOrder));
    }
  }

  private createFromForm(): IShippingOrder {
    return {
      ...new ShippingOrder(),
      id: this.editForm.get(['id'])!.value,
      shippingClaimId: this.editForm.get(['shippingClaimId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingOrder>>): void {
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

  trackById(index: number, item: IShippingClaim): any {
    return item.id;
  }
}
