import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimMissingDocumentComponent } from './claim-missing-document.component';
import { ClaimMissingDocumentDetailComponent } from './claim-missing-document-detail.component';
import { ClaimMissingDocumentUpdateComponent } from './claim-missing-document-update.component';
import { ClaimMissingDocumentDeleteDialogComponent } from './claim-missing-document-delete-dialog.component';
import { claimMissingDocumentRoute } from './claim-missing-document.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimMissingDocumentRoute)],
  declarations: [
    ClaimMissingDocumentComponent,
    ClaimMissingDocumentDetailComponent,
    ClaimMissingDocumentUpdateComponent,
    ClaimMissingDocumentDeleteDialogComponent
  ],
  entryComponents: [ClaimMissingDocumentDeleteDialogComponent]
})
export class EshipperClaimMissingDocumentModule {}
