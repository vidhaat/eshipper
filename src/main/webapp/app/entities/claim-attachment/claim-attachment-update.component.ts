import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClaimAttachment, ClaimAttachment } from 'app/shared/model/claim-attachment.model';
import { ClaimAttachmentService } from './claim-attachment.service';

@Component({
  selector: 'jhi-claim-attachment-update',
  templateUrl: './claim-attachment-update.component.html'
})
export class ClaimAttachmentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    attachmentPath: []
  });

  constructor(
    protected claimAttachmentService: ClaimAttachmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimAttachment }) => {
      this.updateForm(claimAttachment);
    });
  }

  updateForm(claimAttachment: IClaimAttachment): void {
    this.editForm.patchValue({
      id: claimAttachment.id,
      attachmentPath: claimAttachment.attachmentPath
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimAttachment = this.createFromForm();
    if (claimAttachment.id !== undefined) {
      this.subscribeToSaveResponse(this.claimAttachmentService.update(claimAttachment));
    } else {
      this.subscribeToSaveResponse(this.claimAttachmentService.create(claimAttachment));
    }
  }

  private createFromForm(): IClaimAttachment {
    return {
      ...new ClaimAttachment(),
      id: this.editForm.get(['id'])!.value,
      attachmentPath: this.editForm.get(['attachmentPath'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimAttachment>>): void {
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
