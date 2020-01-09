import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimAttachmentComponent } from './claim-attachment.component';
import { ClaimAttachmentDetailComponent } from './claim-attachment-detail.component';
import { ClaimAttachmentUpdateComponent } from './claim-attachment-update.component';
import { ClaimAttachmentDeleteDialogComponent } from './claim-attachment-delete-dialog.component';
import { claimAttachmentRoute } from './claim-attachment.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimAttachmentRoute)],
  declarations: [
    ClaimAttachmentComponent,
    ClaimAttachmentDetailComponent,
    ClaimAttachmentUpdateComponent,
    ClaimAttachmentDeleteDialogComponent
  ],
  entryComponents: [ClaimAttachmentDeleteDialogComponent]
})
export class EshipperClaimAttachmentModule {}
