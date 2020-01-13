import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ClaimSolutionComponent } from './claim-solution.component';
import { ClaimSolutionDetailComponent } from './claim-solution-detail.component';
import { ClaimSolutionUpdateComponent } from './claim-solution-update.component';
import { ClaimSolutionDeleteDialogComponent } from './claim-solution-delete-dialog.component';
import { claimSolutionRoute } from './claim-solution.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(claimSolutionRoute)],
  declarations: [ClaimSolutionComponent, ClaimSolutionDetailComponent, ClaimSolutionUpdateComponent, ClaimSolutionDeleteDialogComponent],
  entryComponents: [ClaimSolutionDeleteDialogComponent]
})
export class EshipperClaimSolutionModule {}
