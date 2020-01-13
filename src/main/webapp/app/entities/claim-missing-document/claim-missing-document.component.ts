import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { ClaimMissingDocumentService } from './claim-missing-document.service';
import { ClaimMissingDocumentDeleteDialogComponent } from './claim-missing-document-delete-dialog.component';

@Component({
  selector: 'jhi-claim-missing-document',
  templateUrl: './claim-missing-document.component.html'
})
export class ClaimMissingDocumentComponent implements OnInit, OnDestroy {
  claimMissingDocuments?: IClaimMissingDocument[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimMissingDocumentService: ClaimMissingDocumentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimMissingDocumentService.query().subscribe((res: HttpResponse<IClaimMissingDocument[]>) => {
      this.claimMissingDocuments = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimMissingDocuments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimMissingDocument): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimMissingDocuments(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimMissingDocumentListModification', () => this.loadAll());
  }

  delete(claimMissingDocument: IClaimMissingDocument): void {
    const modalRef = this.modalService.open(ClaimMissingDocumentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimMissingDocument = claimMissingDocument;
  }
}
