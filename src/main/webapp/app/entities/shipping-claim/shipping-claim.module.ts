import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ShippingClaimComponent } from './shipping-claim.component';
import { ShippingClaimDetailComponent } from './shipping-claim-detail.component';
import { ShippingClaimUpdateComponent } from './shipping-claim-update.component';
import { ShippingClaimDeleteDialogComponent } from './shipping-claim-delete-dialog.component';
import { shippingClaimRoute } from './shipping-claim.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(shippingClaimRoute)],
  declarations: [ShippingClaimComponent, ShippingClaimDetailComponent, ShippingClaimUpdateComponent, ShippingClaimDeleteDialogComponent],
  entryComponents: [ShippingClaimDeleteDialogComponent]
})
export class EshipperShippingClaimModule {}
