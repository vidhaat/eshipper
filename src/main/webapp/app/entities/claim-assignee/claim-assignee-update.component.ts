import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClaimAssignee, ClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { ClaimAssigneeService } from './claim-assignee.service';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';

@Component({
  selector: 'jhi-claim-assignee-update',
  templateUrl: './claim-assignee-update.component.html'
})
export class ClaimAssigneeUpdateComponent implements OnInit {
  isSaving = false;

  shippingclaims: IShippingClaim[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    shippingClaimId: []
  });

  constructor(
    protected claimAssigneeService: ClaimAssigneeService,
    protected shippingClaimService: ShippingClaimService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimAssignee }) => {
      this.updateForm(claimAssignee);

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

  updateForm(claimAssignee: IClaimAssignee): void {
    this.editForm.patchValue({
      id: claimAssignee.id,
      name: claimAssignee.name,
      shippingClaimId: claimAssignee.shippingClaimId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimAssignee = this.createFromForm();
    if (claimAssignee.id !== undefined) {
      this.subscribeToSaveResponse(this.claimAssigneeService.update(claimAssignee));
    } else {
      this.subscribeToSaveResponse(this.claimAssigneeService.create(claimAssignee));
    }
  }

  private createFromForm(): IClaimAssignee {
    return {
      ...new ClaimAssignee(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      shippingClaimId: this.editForm.get(['shippingClaimId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimAssignee>>): void {
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
