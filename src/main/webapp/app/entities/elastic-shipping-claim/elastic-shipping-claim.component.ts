import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IElasticShippingClaim } from 'app/shared/model/elastic-shipping-claim.model';
import { ElasticShippingClaimService } from './elastic-shipping-claim.service';
import { ElasticShippingClaimDeleteDialogComponent } from './elastic-shipping-claim-delete-dialog.component';

@Component({
  selector: 'jhi-elastic-shipping-claim',
  templateUrl: './elastic-shipping-claim.component.html'
})
export class ElasticShippingClaimComponent implements OnInit, OnDestroy {
  elasticShippingClaims?: IElasticShippingClaim[];
  eventSubscriber?: Subscription;

  constructor(
    protected elasticShippingClaimService: ElasticShippingClaimService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.elasticShippingClaimService.query().subscribe((res: HttpResponse<IElasticShippingClaim[]>) => {
      this.elasticShippingClaims = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInElasticShippingClaims();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IElasticShippingClaim): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInElasticShippingClaims(): void {
    this.eventSubscriber = this.eventManager.subscribe('elasticShippingClaimListModification', () => this.loadAll());
  }

  delete(elasticShippingClaim: IElasticShippingClaim): void {
    const modalRef = this.modalService.open(ElasticShippingClaimDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.elasticShippingClaim = elasticShippingClaim;
  }
}
