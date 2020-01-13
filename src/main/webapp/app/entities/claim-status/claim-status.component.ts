import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimStatus } from 'app/shared/model/claim-status.model';
import { ClaimStatusService } from './claim-status.service';
import { ClaimStatusDeleteDialogComponent } from './claim-status-delete-dialog.component';

@Component({
  selector: 'jhi-claim-status',
  templateUrl: './claim-status.component.html'
})
export class ClaimStatusComponent implements OnInit, OnDestroy {
  claimStatuses?: IClaimStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimStatusService: ClaimStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimStatusService.query().subscribe((res: HttpResponse<IClaimStatus[]>) => {
      this.claimStatuses = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimStatusListModification', () => this.loadAll());
  }

  delete(claimStatus: IClaimStatus): void {
    const modalRef = this.modalService.open(ClaimStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimStatus = claimStatus;
  }
}
