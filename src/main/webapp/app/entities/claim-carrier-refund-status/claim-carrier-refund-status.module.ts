import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimCarrierRefundStatusComponent } from './claim-carrier-refund-status.component';
import { ClaimCarrierRefundStatusDetailComponent } from './claim-carrier-refund-status-detail.component';
import { ClaimCarrierRefundStatusUpdateComponent } from './claim-carrier-refund-status-update.component';
import { ClaimCarrierRefundStatusDeleteDialogComponent } from './claim-carrier-refund-status-delete-dialog.component';
import { claimCarrierRefundStatusRoute } from './claim-carrier-refund-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimCarrierRefundStatusRoute)],
  declarations: [
    ClaimCarrierRefundStatusComponent,
    ClaimCarrierRefundStatusDetailComponent,
    ClaimCarrierRefundStatusUpdateComponent,
    ClaimCarrierRefundStatusDeleteDialogComponent
  ],
  entryComponents: [ClaimCarrierRefundStatusDeleteDialogComponent]
})
export class EshipperClaimCarrierRefundStatusModule {}
