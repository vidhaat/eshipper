import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { ClaimAssigneeService } from './claim-assignee.service';
import { ClaimAssigneeDeleteDialogComponent } from './claim-assignee-delete-dialog.component';

@Component({
  selector: 'jhi-claim-assignee',
  templateUrl: './claim-assignee.component.html'
})
export class ClaimAssigneeComponent implements OnInit, OnDestroy {
  claimAssignees?: IClaimAssignee[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimAssigneeService: ClaimAssigneeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimAssigneeService.query().subscribe((res: HttpResponse<IClaimAssignee[]>) => {
      this.claimAssignees = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimAssignees();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimAssignee): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimAssignees(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimAssigneeListModification', () => this.loadAll());
  }

  delete(claimAssignee: IClaimAssignee): void {
    const modalRef = this.modalService.open(ClaimAssigneeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimAssignee = claimAssignee;
  }
}
