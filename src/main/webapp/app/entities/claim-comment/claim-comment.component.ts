import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimComment } from 'app/shared/model/claim-comment.model';
import { ClaimCommentService } from './claim-comment.service';
import { ClaimCommentDeleteDialogComponent } from './claim-comment-delete-dialog.component';

@Component({
  selector: 'jhi-claim-comment',
  templateUrl: './claim-comment.component.html'
})
export class ClaimCommentComponent implements OnInit, OnDestroy {
  claimComments?: IClaimComment[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimCommentService: ClaimCommentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimCommentService.query().subscribe((res: HttpResponse<IClaimComment[]>) => {
      this.claimComments = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimComments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimComment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimComments(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimCommentListModification', () => this.loadAll());
  }

  delete(claimComment: IClaimComment): void {
    const modalRef = this.modalService.open(ClaimCommentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimComment = claimComment;
  }
}
