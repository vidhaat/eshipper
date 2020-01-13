import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimEshipperRefundComponent } from './claim-eshipper-refund.component';
import { ClaimEshipperRefundDetailComponent } from './claim-eshipper-refund-detail.component';
import { ClaimEshipperRefundUpdateComponent } from './claim-eshipper-refund-update.component';
import { ClaimEshipperRefundDeleteDialogComponent } from './claim-eshipper-refund-delete-dialog.component';
import { claimEshipperRefundRoute } from './claim-eshipper-refund.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimEshipperRefundRoute)],
  declarations: [
    ClaimEshipperRefundComponent,
    ClaimEshipperRefundDetailComponent,
    ClaimEshipperRefundUpdateComponent,
    ClaimEshipperRefundDeleteDialogComponent
  ],
  entryComponents: [ClaimEshipperRefundDeleteDialogComponent]
})
export class EshipperClaimEshipperRefundModule {}
