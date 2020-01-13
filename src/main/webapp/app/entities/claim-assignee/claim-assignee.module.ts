import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimAssigneeComponent } from './claim-assignee.component';
import { ClaimAssigneeDetailComponent } from './claim-assignee-detail.component';
import { ClaimAssigneeUpdateComponent } from './claim-assignee-update.component';
import { ClaimAssigneeDeleteDialogComponent } from './claim-assignee-delete-dialog.component';
import { claimAssigneeRoute } from './claim-assignee.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimAssigneeRoute)],
  declarations: [ClaimAssigneeComponent, ClaimAssigneeDetailComponent, ClaimAssigneeUpdateComponent, ClaimAssigneeDeleteDialogComponent],
  entryComponents: [ClaimAssigneeDeleteDialogComponent]
})
export class EshipperClaimAssigneeModule {}
