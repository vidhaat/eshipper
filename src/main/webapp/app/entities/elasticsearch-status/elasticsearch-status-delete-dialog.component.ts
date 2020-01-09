import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElasticsearchStatus } from 'app/shared/model/elasticsearch-status.model';
import { ElasticsearchStatusService } from './elasticsearch-status.service';

@Component({
  templateUrl: './elasticsearch-status-delete-dialog.component.html'
})
export class ElasticsearchStatusDeleteDialogComponent {
  elasticsearchStatus?: IElasticsearchStatus;

  constructor(
    protected elasticsearchStatusService: ElasticsearchStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.elasticsearchStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('elasticsearchStatusListModification');
      this.activeModal.close();
    });
  }
}
