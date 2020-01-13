import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimCommentComponent } from './claim-comment.component';
import { ClaimCommentDetailComponent } from './claim-comment-detail.component';
import { ClaimCommentUpdateComponent } from './claim-comment-update.component';
import { ClaimCommentDeleteDialogComponent } from './claim-comment-delete-dialog.component';
import { claimCommentRoute } from './claim-comment.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimCommentRoute)],
  declarations: [ClaimCommentComponent, ClaimCommentDetailComponent, ClaimCommentUpdateComponent, ClaimCommentDeleteDialogComponent],
  entryComponents: [ClaimCommentDeleteDialogComponent]
})
export class EshipperClaimCommentModule {}
