import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';
import { ElasticsearchStatusService } from './elasticsearch-status.service';
import { ElasticsearchStatusDeleteDialogComponent } from './elasticsearch-status-delete-dialog.component';

@Component({
  selector: 'jhi-elasticsearch-status',
  templateUrl: './elasticsearch-status.component.html'
})
export class ElasticsearchStatusComponent implements OnInit, OnDestroy {
  elasticsearchStatuses?: IElasticsearchStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected elasticsearchStatusService: ElasticsearchStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.elasticsearchStatusService.query().subscribe((res: HttpResponse<IElasticsearchStatus[]>) => {
      this.elasticsearchStatuses = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInElasticsearchStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IElasticsearchStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInElasticsearchStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('elasticsearchStatusListModification', () => this.loadAll());
  }

  delete(elasticsearchStatus: IElasticsearchStatus): void {
    const modalRef = this.modalService.open(ElasticsearchStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.elasticsearchStatus = elasticsearchStatus;
  }
}
