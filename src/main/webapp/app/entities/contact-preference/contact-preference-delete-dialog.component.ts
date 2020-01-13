import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContactPreference } from 'app/shared/model/contact-preference.model';
import { ContactPreferenceService } from './contact-preference.service';

@Component({
  templateUrl: './contact-preference-delete-dialog.component.html'
})
export class ContactPreferenceDeleteDialogComponent {
  contactPreference?: IContactPreference;

  constructor(
    protected contactPreferenceService: ContactPreferenceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contactPreferenceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contactPreferenceListModification');
      this.activeModal.close();
    });
  }
}
