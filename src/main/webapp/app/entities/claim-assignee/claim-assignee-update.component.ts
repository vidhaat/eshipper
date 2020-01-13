import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaimAssignee, ClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { ClaimAssigneeService } from './claim-assignee.service';

@Component({
  selector: 'jhi-claim-assignee-update',
  templateUrl: './claim-assignee-update.component.html'
})
export class ClaimAssigneeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: []
  });

  constructor(protected claimAssigneeService: ClaimAssigneeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimAssignee }) => {
      this.updateForm(claimAssignee);
    });
  }

  updateForm(claimAssignee: IClaimAssignee): void {
    this.editForm.patchValue({
      id: claimAssignee.id,
      name: claimAssignee.name
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
      name: this.editForm.get(['name'])!.value
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
}
