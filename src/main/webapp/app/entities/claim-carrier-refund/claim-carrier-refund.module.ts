import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimCarrierRefundComponent } from './claim-carrier-refund.component';
import { ClaimCarrierRefundDetailComponent } from './claim-carrier-refund-detail.component';
import { ClaimCarrierRefundUpdateComponent } from './claim-carrier-refund-update.component';
import { ClaimCarrierRefundDeleteDialogComponent } from './claim-carrier-refund-delete-dialog.component';
import { claimCarrierRefundRoute } from './claim-carrier-refund.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimCarrierRefundRoute)],
  declarations: [
    ClaimCarrierRefundComponent,
    ClaimCarrierRefundDetailComponent,
    ClaimCarrierRefundUpdateComponent,
    ClaimCarrierRefundDeleteDialogComponent
  ],
  entryComponents: [ClaimCarrierRefundDeleteDialogComponent]
})
export class EshipperClaimCarrierRefundModule {}
