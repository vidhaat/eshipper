import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClaimDocumentType } from 'app/shared/model/claim-document-type.model';
import { ClaimDocumentTypeService } from './claim-document-type.service';
import { ClaimDocumentTypeDeleteDialogComponent } from './claim-document-type-delete-dialog.component';

@Component({
  selector: 'jhi-claim-document-type',
  templateUrl: './claim-document-type.component.html'
})
export class ClaimDocumentTypeComponent implements OnInit, OnDestroy {
  claimDocumentTypes?: IClaimDocumentType[];
  eventSubscriber?: Subscription;

  constructor(
    protected claimDocumentTypeService: ClaimDocumentTypeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.claimDocumentTypeService.query().subscribe((res: HttpResponse<IClaimDocumentType[]>) => {
      this.claimDocumentTypes = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClaimDocumentTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClaimDocumentType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClaimDocumentTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('claimDocumentTypeListModification', () => this.loadAll());
  }

  delete(claimDocumentType: IClaimDocumentType): void {
    const modalRef = this.modalService.open(ClaimDocumentTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.claimDocumentType = claimDocumentType;
  }
}
