import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimSolution } from 'app/shared/model/claim-solution.model';
import { ClaimSolutionService } from './claim-solution.service';
import { ClaimSolutionDeleteDialogComponent } from './claim-solution-delete-dialog.component';

@Component({
  selector: 'jhi-claim-solution',
  templateUrl: './claim-solution.component.html'
})
export class ClaimSolutionComponent implements OnInit, OnDestroy {
  claimSolutions?: IClaimSolution[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimSolutionService: ClaimSolutionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimSolutionService.query().subscribe((res: HttpResponse<IClaimSolution[]>) => {
      this.claimSolutions = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimSolutions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimSolution): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimSolutions(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimSolutionListModification', () => this.loadAll());
  }

  delete(claimSolution: IClaimSolution): void {
    const modalRef = this.modalService.open(ClaimSolutionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimSolution = claimSolution;
  }
}
