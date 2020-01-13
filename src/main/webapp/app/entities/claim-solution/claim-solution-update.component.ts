import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaimSolution, ClaimSolution } from 'app/shared/model/claim-solution.model';
import { ClaimSolutionService } from './claim-solution.service';

@Component({
  selector: 'jhi-claim-solution-update',
  templateUrl: './claim-solution-update.component.html'
})
export class ClaimSolutionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: []
  });

  constructor(protected claimSolutionService: ClaimSolutionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimSolution }) => {
      this.updateForm(claimSolution);
    });
  }

  updateForm(claimSolution: IClaimSolution): void {
    this.editForm.patchValue({
      id: claimSolution.id,
      name: claimSolution.name
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
      name: this.editForm.get(['name'])!.value
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
}
