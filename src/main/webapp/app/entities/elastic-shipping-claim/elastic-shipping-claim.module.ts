import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ElasticShippingClaimComponent } from './elastic-shipping-claim.component';
import { ElasticShippingClaimDetailComponent } from './elastic-shipping-claim-detail.component';
import { ElasticShippingClaimUpdateComponent } from './elastic-shipping-claim-update.component';
import { ElasticShippingClaimDeleteDialogComponent } from './elastic-shipping-claim-delete-dialog.component';
import { elasticShippingClaimRoute } from './elastic-shipping-claim.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(elasticShippingClaimRoute)],
  declarations: [
    ElasticShippingClaimComponent,
    ElasticShippingClaimDetailComponent,
    ElasticShippingClaimUpdateComponent,
    ElasticShippingClaimDeleteDialogComponent
  ],
  entryComponents: [ElasticShippingClaimDeleteDialogComponent]
})
export class EshipperElasticShippingClaimModule {}
