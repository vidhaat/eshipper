import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITicketReason, TicketReason } from 'app/shared/model/ticket-reason.model';
import { TicketReasonService } from './ticket-reason.service';

@Component({
  selector: 'jhi-ticket-reason-update',
  templateUrl: './ticket-reason-update.component.html'
})
export class TicketReasonUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected ticketReasonService: TicketReasonService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ticketReason }) => {
      this.updateForm(ticketReason);
    });
  }

  updateForm(ticketReason: ITicketReason): void {
    this.editForm.patchValue({
      id: ticketReason.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ticketReason = this.createFromForm();
    if (ticketReason.id !== undefined) {
      this.subscribeToSaveResponse(this.ticketReasonService.update(ticketReason));
    } else {
      this.subscribeToSaveResponse(this.ticketReasonService.create(ticketReason));
    }
  }

  private createFromForm(): ITicketReason {
    return {
      ...new TicketReason(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITicketReason>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
