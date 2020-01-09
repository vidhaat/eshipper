import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClaimSolution, ClaimSolution } from 'app/shared/model/claim-solution.model';
import { ClaimSolutionService } from './claim-solution.service';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';

@Component({
  selector: 'jhi-claim-solution-update',
  templateUrl: './claim-solution-update.component.html'
})
export class ClaimSolutionUpdateComponent implements OnInit {
  isSaving = false;

  shippingclaims: IShippingClaim[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    shippingClaimId: []
  });

  constructor(
    protected claimSolutionService: ClaimSolutionService,
    protected shippingClaimService: ShippingClaimService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimSolution }) => {
      this.updateForm(claimSolution);

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

  updateForm(claimSolution: IClaimSolution): void {
    this.editForm.patchValue({
      id: claimSolution.id,
      name: claimSolution.name,
      shippingClaimId: claimSolution.shippingClaimId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimSolution = this.createFromForm();
    if (claimSolution.id !== undefined) {
      this.subscribeToSaveResponse(this.claimSolutionService.update(claimSolution));
    } else {
      this.subscribeToSaveResponse(this.claimSolutionService.create(claimSolution));
    }
  }

  private createFromForm(): IClaimSolution {
    return {
      ...new ClaimSolution(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      shippingClaimId: this.editForm.get(['shippingClaimId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimSolution>>): void {
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
