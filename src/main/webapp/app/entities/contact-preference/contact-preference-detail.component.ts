import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContactPreference } from 'app/shared/model/contact-preference.model';

@Component({
  selector: 'jhi-contact-preference-detail',
  templateUrl: './contact-preference-detail.component.html'
})
export class ContactPreferenceDetailComponent implements OnInit {
  contactPreference: IContactPreference | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactPreference }) => {
      this.contactPreference = contactPreference;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
