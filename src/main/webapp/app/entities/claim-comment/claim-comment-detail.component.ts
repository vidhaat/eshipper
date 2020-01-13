import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClaimComment } from 'app/shared/model/claim-comment.model';

@Component({
  selector: 'jhi-claim-comment-detail',
  templateUrl: './claim-comment-detail.component.html'
})
export class ClaimCommentDetailComponent implements OnInit {
  claimComment: IClaimComment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ claimComment }) => {
      this.claimComment = claimComment;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
