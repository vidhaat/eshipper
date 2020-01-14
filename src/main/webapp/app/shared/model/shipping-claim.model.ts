import { Moment } from 'moment';
import { IClaimAttachment } from 'app/shared/model/claim-attachment.model';
import { IClaimMissingDocument } from 'app/shared/model/claim-missing-document.model';
import { IClaimComment } from 'app/shared/model/claim-comment.model';

export interface IShippingClaim {
  id?: number;
  receivedDate?: Moment;
  mailedDate?: Moment;
  createdDate?: Moment;
  updatedDate?: Moment;
  trackingNumber?: string;
  subject?: string;
  description?: string;
  notifyCustomer?: boolean;
  missingDocuments?: boolean;
  claimCarrierRefundId?: number;
  claimEshipperRefundId?: number;
  claimAttachments?: IClaimAttachment[];
  claimMissingDocuments?: IClaimMissingDocument[];
  claimComments?: IClaimComment[];
  shippingOrderId?: number;
  ticketReasonId?: number;
  claimStatusId?: number;
  claimSolutionId?: number;
  claimAssigneeId?: number;
  contactPreferenceId?: number;
}

export class ShippingClaim implements IShippingClaim {
  constructor(
    public id?: number,
    public receivedDate?: Moment,
    public mailedDate?: Moment,
    public createdDate?: Moment,
    public updatedDate?: Moment,
    public trackingNumber?: string,
    public subject?: string,
    public description?: string,
    public notifyCustomer?: boolean,
    public missingDocuments?: boolean,
    public claimCarrierRefundId?: number,
    public claimEshipperRefundId?: number,
    public claimAttachments?: IClaimAttachment[],
    public claimMissingDocuments?: IClaimMissingDocument[],
    public claimComments?: IClaimComment[],
    public shippingOrderId?: number,
    public ticketReasonId?: number,
    public claimStatusId?: number,
    public claimSolutionId?: number,
    public claimAssigneeId?: number,
    public contactPreferenceId?: number
  ) {
    this.notifyCustomer = this.notifyCustomer || false;
    this.missingDocuments = this.missingDocuments || false;
  }
}
