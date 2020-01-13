import { Moment } from 'moment';

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
  shippingOrderId?: number;
  ticketReasonId?: number;
  claimStatusId?: number;
  claimSolutionId?: number;
  claimAssigneeId?: number;
  claimCommentId?: number;
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
    public shippingOrderId?: number,
    public ticketReasonId?: number,
    public claimStatusId?: number,
    public claimSolutionId?: number,
    public claimAssigneeId?: number,
    public claimCommentId?: number,
    public contactPreferenceId?: number
  ) {
    this.notifyCustomer = this.notifyCustomer || false;
    this.missingDocuments = this.missingDocuments || false;
  }
}
