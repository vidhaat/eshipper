import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimAttachment } from 'app/shared/model/claim-attachment.model';
import { ClaimAttachmentService } from './claim-attachment.service';
import { ClaimAttachmentDeleteDialogComponent } from './claim-attachment-delete-dialog.component';

@Component({
  selector: 'jhi-claim-attachment',
  templateUrl: './claim-attachment.component.html'
})
export class ClaimAttachmentComponent implements OnInit, OnDestroy {
  claimAttachments?: IClaimAttachment[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimAttachmentService: ClaimAttachmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimAttachmentService.query().subscribe((res: HttpResponse<IClaimAttachment[]>) => {
      this.claimAttachments = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimAttachments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimAttachment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimAttachments(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimAttachmentListModification', () => this.loadAll());
  }

  delete(claimAttachment: IClaimAttachment): void {
    const modalRef = this.modalService.open(ClaimAttachmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimAttachment = claimAttachment;
  }
}
