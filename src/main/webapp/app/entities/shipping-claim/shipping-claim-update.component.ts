import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IShippingClaim, ShippingClaim } from 'app/shared/model/shipping-claim.model';
import { ShippingClaimService } from './shipping-claim.service';
import { IClaimCarrierRefund } from 'app/shared/model/claim-carrier-refund.model';
import { ClaimCarrierRefundService } from 'app/entities/claim-carrier-refund/claim-carrier-refund.service';
import { IClaimEshipperRefund } from 'app/shared/model/claim-eshipper-refund.model';
import { ClaimEshipperRefundService } from 'app/entities/claim-eshipper-refund/claim-eshipper-refund.service';
import { IShippingOrder } from 'app/shared/model/shipping-order.model';
import { ShippingOrderService } from 'app/entities/shipping-order/shipping-order.service';
import { ITicketReason } from 'app/shared/model/ticket-reason.model';
import { TicketReasonService } from 'app/entities/ticket-reason/ticket-reason.service';
import { IClaimStatus } from 'app/shared/model/claim-status.model';
import { ClaimStatusService } from 'app/entities/claim-status/claim-status.service';
import { IClaimSolution } from 'app/shared/model/claim-solution.model';
import { ClaimSolutionService } from 'app/entities/claim-solution/claim-solution.service';
import { IClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { ClaimAssigneeService } from 'app/entities/claim-assignee/claim-assignee.service';
import { IContactPreference } from 'app/shared/model/contact-preference.model';
import { ContactPreferenceService } from 'app/entities/contact-preference/contact-preference.service';

type SelectableEntity =
  | IClaimCarrierRefund
  | IClaimEshipperRefund
  | IShippingOrder
  | ITicketReason
  | IClaimStatus
  | IClaimSolution
  | IClaimAssignee
  | IContactPreference;

@Component({
  selector: 'jhi-shipping-claim-update',
  templateUrl: './shipping-claim-update.component.html'
})
export class ShippingClaimUpdateComponent implements OnInit {
  isSaving = false;

  claimcarrierrefunds: IClaimCarrierRefund[] = [];

  claimeshipperrefunds: IClaimEshipperRefund[] = [];

  shippingorders: IShippingOrder[] = [];

  ticketreasons: ITicketReason[] = [];

  claimstatuses: IClaimStatus[] = [];

  claimsolutions: IClaimSolution[] = [];

  claimassignees: IClaimAssignee[] = [];

  contactpreferences: IContactPreference[] = [];

  editForm = this.fb.group({
    id: [],
    receivedDate: [],
    mailedDate: [],
    createdDate: [],
    updatedDate: [],
    trackingNumber: [],
    subject: [],
    description: [],
    notifyCustomer: [],
    missingDocuments: [],
    claimCarrierRefundId: [],
    claimEshipperRefundId: [],
    shippingOrderId: [],
    ticketReasonId: [],
    claimStatusId: [],
    claimSolutionId: [],
    claimAssigneeId: [],
    contactPreferenceId: []
  });

  constructor(
    protected shippingClaimService: ShippingClaimService,
    protected claimCarrierRefundService: ClaimCarrierRefundService,
    protected claimEshipperRefundService: ClaimEshipperRefundService,
    protected shippingOrderService: ShippingOrderService,
    protected ticketReasonService: TicketReasonService,
    protected claimStatusService: ClaimStatusService,
    protected claimSolutionService: ClaimSolutionService,
    protected claimAssigneeService: ClaimAssigneeService,
    protected contactPreferenceService: ContactPreferenceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingClaim }) => {
      this.updateForm(shippingClaim);

      this.claimCarrierRefundService
        .query({ filter: 'shippingclaim-is-null' })
        .pipe(
          map((res: HttpResponse<IClaimCarrierRefund[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimCarrierRefund[]) => {
          if (!shippingClaim.claimCarrierRefundId) {
            this.claimcarrierrefunds = resBody;
          } else {
            this.claimCarrierRefundService
              .find(shippingClaim.claimCarrierRefundId)
              .pipe(
                map((subRes: HttpResponse<IClaimCarrierRefund>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IClaimCarrierRefund[]) => {
                this.claimcarrierrefunds = concatRes;
              });
          }
        });

      this.claimEshipperRefundService
        .query({ filter: 'shippingclaim-is-null' })
        .pipe(
          map((res: HttpResponse<IClaimEshipperRefund[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimEshipperRefund[]) => {
          if (!shippingClaim.claimEshipperRefundId) {
            this.claimeshipperrefunds = resBody;
          } else {
            this.claimEshipperRefundService
              .find(shippingClaim.claimEshipperRefundId)
              .pipe(
                map((subRes: HttpResponse<IClaimEshipperRefund>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IClaimEshipperRefund[]) => {
                this.claimeshipperrefunds = concatRes;
              });
          }
        });

      this.shippingOrderService
        .query()
        .pipe(
          map((res: HttpResponse<IShippingOrder[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IShippingOrder[]) => (this.shippingorders = resBody));

      this.ticketReasonService
        .query()
        .pipe(
          map((res: HttpResponse<ITicketReason[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ITicketReason[]) => (this.ticketreasons = resBody));

      this.claimStatusService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimStatus[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimStatus[]) => (this.claimstatuses = resBody));

      this.claimSolutionService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimSolution[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimSolution[]) => (this.claimsolutions = resBody));

      this.claimAssigneeService
        .query()
        .pipe(
          map((res: HttpResponse<IClaimAssignee[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IClaimAssignee[]) => (this.claimassignees = resBody));

      this.contactPreferenceService
        .query()
        .pipe(
          map((res: HttpResponse<IContactPreference[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IContactPreference[]) => (this.contactpreferences = resBody));
    });
  }

  updateForm(shippingClaim: IShippingClaim): void {
    this.editForm.patchValue({
      id: shippingClaim.id,
      receivedDate: shippingClaim.receivedDate != null ? shippingClaim.receivedDate.format(DATE_TIME_FORMAT) : null,
      mailedDate: shippingClaim.mailedDate != null ? shippingClaim.mailedDate.format(DATE_TIME_FORMAT) : null,
      createdDate: shippingClaim.createdDate != null ? shippingClaim.createdDate.format(DATE_TIME_FORMAT) : null,
      updatedDate: shippingClaim.updatedDate != null ? shippingClaim.updatedDate.format(DATE_TIME_FORMAT) : null,
      trackingNumber: shippingClaim.trackingNumber,
      subject: shippingClaim.subject,
      description: shippingClaim.description,
      notifyCustomer: shippingClaim.notifyCustomer,
      missingDocuments: shippingClaim.missingDocuments,
      claimCarrierRefundId: shippingClaim.claimCarrierRefundId,
      claimEshipperRefundId: shippingClaim.claimEshipperRefundId,
      shippingOrderId: shippingClaim.shippingOrderId,
      ticketReasonId: shippingClaim.ticketReasonId,
      claimStatusId: shippingClaim.claimStatusId,
      claimSolutionId: shippingClaim.claimSolutionId,
      claimAssigneeId: shippingClaim.claimAssigneeId,
      contactPreferenceId: shippingClaim.contactPreferenceId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shippingClaim = this.createFromForm();
    if (shippingClaim.id !== undefined) {
      this.subscribeToSaveResponse(this.shippingClaimService.update(shippingClaim));
    } else {
      this.subscribeToSaveResponse(this.shippingClaimService.create(shippingClaim));
    }
  }

  private createFromForm(): IShippingClaim {
    return {
      ...new ShippingClaim(),
      id: this.editForm.get(['id'])!.value,
      receivedDate:
        this.editForm.get(['receivedDate'])!.value != null
          ? moment(this.editForm.get(['receivedDate'])!.value, DATE_TIME_FORMAT)
          : undefined,
      mailedDate:
        this.editForm.get(['mailedDate'])!.value != null ? moment(this.editForm.get(['mailedDate'])!.value, DATE_TIME_FORMAT) : undefined,
      createdDate:
        this.editForm.get(['createdDate'])!.value != null ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedDate:
        this.editForm.get(['updatedDate'])!.value != null ? moment(this.editForm.get(['updatedDate'])!.value, DATE_TIME_FORMAT) : undefined,
      trackingNumber: this.editForm.get(['trackingNumber'])!.value,
      subject: this.editForm.get(['subject'])!.value,
      description: this.editForm.get(['description'])!.value,
      notifyCustomer: this.editForm.get(['notifyCustomer'])!.value,
      missingDocuments: this.editForm.get(['missingDocuments'])!.value,
      claimCarrierRefundId: this.editForm.get(['claimCarrierRefundId'])!.value,
      claimEshipperRefundId: this.editForm.get(['claimEshipperRefundId'])!.value,
      shippingOrderId: this.editForm.get(['shippingOrderId'])!.value,
      ticketReasonId: this.editForm.get(['ticketReasonId'])!.value,
      claimStatusId: this.editForm.get(['claimStatusId'])!.value,
      claimSolutionId: this.editForm.get(['claimSolutionId'])!.value,
      claimAssigneeId: this.editForm.get(['claimAssigneeId'])!.value,
      contactPreferenceId: this.editForm.get(['contactPreferenceId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingClaim>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
