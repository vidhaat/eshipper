import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IContactPreference, ContactPreference } from 'app/shared/model/contact-preference.model';
import { ContactPreferenceService } from './contact-preference.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-contact-preference-update',
  templateUrl: './contact-preference-update.component.html'
})
export class ContactPreferenceUpdateComponent implements OnInit {
  isSaving = false;

  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    userId: []
  });

  constructor(
    protected contactPreferenceService: ContactPreferenceService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactPreference }) => {
      this.updateForm(contactPreference);

      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => (this.users = resBody));
    });
  }

  updateForm(contactPreference: IContactPreference): void {
    this.editForm.patchValue({
      id: contactPreference.id,
      userId: contactPreference.userId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contactPreference = this.createFromForm();
    if (contactPreference.id !== undefined) {
      this.subscribeToSaveResponse(this.contactPreferenceService.update(contactPreference));
    } else {
      this.subscribeToSaveResponse(this.contactPreferenceService.create(contactPreference));
    }
  }

  private createFromForm(): IContactPreference {
    return {
      ...new ContactPreference(),
      id: this.editForm.get(['id'])!.value,
      userId: this.editForm.get(['userId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContactPreference>>): void {
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

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
