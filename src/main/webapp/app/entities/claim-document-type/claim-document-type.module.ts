import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimDocumentTypeComponent } from './claim-document-type.component';
import { ClaimDocumentTypeDetailComponent } from './claim-document-type-detail.component';
import { ClaimDocumentTypeUpdateComponent } from './claim-document-type-update.component';
import { ClaimDocumentTypeDeleteDialogComponent } from './claim-document-type-delete-dialog.component';
import { claimDocumentTypeRoute } from './claim-document-type.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimDocumentTypeRoute)],
  declarations: [
    ClaimDocumentTypeComponent,
    ClaimDocumentTypeDetailComponent,
    ClaimDocumentTypeUpdateComponent,
    ClaimDocumentTypeDeleteDialogComponent
  ],
  entryComponents: [ClaimDocumentTypeDeleteDialogComponent]
})
export class EshipperClaimDocumentTypeModule {}
