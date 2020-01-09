import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimStatusComponent } from './claim-status.component';
import { ClaimStatusDetailComponent } from './claim-status-detail.component';
import { ClaimStatusUpdateComponent } from './claim-status-update.component';
import { ClaimStatusDeleteDialogComponent } from './claim-status-delete-dialog.component';
import { claimStatusRoute } from './claim-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimStatusRoute)],
  declarations: [ClaimStatusComponent, ClaimStatusDetailComponent, ClaimStatusUpdateComponent, ClaimStatusDeleteDialogComponent],
  entryComponents: [ClaimStatusDeleteDialogComponent]
})
export class EshipperClaimStatusModule {}
