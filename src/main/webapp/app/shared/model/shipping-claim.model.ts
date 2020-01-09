import { Moment } from 'moment';
import { IShippingOrder } from 'app/shared/model/shipping-order.model';
import { ITicketReason } from 'app/shared/model/ticket-reason.model';
import { IClaimStatus } from 'app/shared/model/claim-status.model';
import { IClaimSolution } from 'app/shared/model/claim-solution.model';
import { IClaimAssignee } from 'app/shared/model/claim-assignee.model';
import { IClaimComment } from 'app/shared/model/claim-comment.model';
import { IContactPreference } from 'app/shared/model/contact-preference.model';

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
  shippingOrders?: IShippingOrder[];
  ticketReasons?: ITicketReason[];
  claimStatuses?: IClaimStatus[];
  claimSolutions?: IClaimSolution[];
  claimAssignees?: IClaimAssignee[];
  claimComments?: IClaimComment[];
  contactPreferences?: IContactPreference[];
  claimAttachmentId?: number;
  claimMissingDocumentId?: number;
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
    public shippingOrders?: IShippingOrder[],
    public ticketReasons?: ITicketReason[],
    public claimStatuses?: IClaimStatus[],
    public claimSolutions?: IClaimSolution[],
    public claimAssignees?: IClaimAssignee[],
    public claimComments?: IClaimComment[],
    public contactPreferences?: IContactPreference[],
    public claimAttachmentId?: number,
    public claimMissingDocumentId?: number
  ) {
    this.notifyCustomer = this.notifyCustomer || false;
    this.missingDocuments = this.missingDocuments || false;
  }
}
