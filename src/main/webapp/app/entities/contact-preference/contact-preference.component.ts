import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContactPreference } from 'app/shared/model/contact-preference.model';
import { ContactPreferenceService } from './contact-preference.service';
import { ContactPreferenceDeleteDialogComponent } from './contact-preference-delete-dialog.component';

@Component({
  selector: 'jhi-contact-preference',
  templateUrl: './contact-preference.component.html'
})
export class ContactPreferenceComponent implements OnInit, OnDestroy {
  contactPreferences?: IContactPreference[];
  eventSubscriber?: Subscription;

  constructor(
    protected contactPreferenceService: ContactPreferenceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.contactPreferenceService.query().subscribe((res: HttpResponse<IContactPreference[]>) => {
      this.contactPreferences = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContactPreferences();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContactPreference): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContactPreferences(): void {
    this.eventSubscriber = this.eventManager.subscribe('contactPreferenceListModification', () => this.loadAll());
  }

  delete(contactPreference: IContactPreference): void {
    const modalRef = this.modalService.open(ContactPreferenceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contactPreference = contactPreference;
  }
}
