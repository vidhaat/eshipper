import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IClaimComment, ClaimComment } from 'app/shared/model/claim-comment.model';
import { ClaimCommentService } from './claim-comment.service';
import { IShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from 'app/entities/shipping-claim/shipping-claim.service';

@Component({
  selector: 'jhi-claim-comment-update',
  templateUrl: './claim-comment-update.component.html'
})
export class ClaimCommentUpdateComponent implements OnInit {
  isSaving = false;

  shippingclaims: IShippingClaim[] = [];

  editForm = this.fb.group({
    id: [],
    header: [],
    description: [],
    date: [],
    commentBy: [],
    shippingClaimId: []
  });

  constructor(
    protected claimCommentService: ClaimCommentService,
    protected shippingClaimService: ShippingClaimService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimComment }) => {
      this.updateForm(claimComment);

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

  updateForm(claimComment: IClaimComment): void {
    this.editForm.patchValue({
      id: claimComment.id,
      header: claimComment.header,
      description: claimComment.description,
      date: claimComment.date != null ? claimComment.date.format(DATE_TIME_FORMAT) : null,
      commentBy: claimComment.commentBy,
      shippingClaimId: claimComment.shippingClaimId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const claimComment = this.createFromForm();
    if (claimComment.id !== undefined) {
      this.subscribeToSaveResponse(this.claimCommentService.update(claimComment));
    } else {
      this.subscribeToSaveResponse(this.claimCommentService.create(claimComment));
    }
  }

  private createFromForm(): IClaimComment {
    return {
      ...new ClaimComment(),
      id: this.editForm.get(['id'])!.value,
      header: this.editForm.get(['header'])!.value,
      description: this.editForm.get(['description'])!.value,
      date: this.editForm.get(['date'])!.value != null ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      commentBy: this.editForm.get(['commentBy'])!.value,
      shippingClaimId: this.editForm.get(['shippingClaimId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClaimComment>>): void {
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
